package in.lakshay.rentACarBackend.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

// service interfaces - need all these for the car manager
import in.lakshay.rentACarBackend.business.abstracts.*;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages; // typo in package name lol
import in.lakshay.rentACarBackend.business.dtos.carDtos.gets.GetCarDto;

// list DTOs for returning car data
import in.lakshay.rentACarBackend.business.dtos.carDtos.lists.*;

// exceptions - omg so many of these!
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions.CarExistsInCarCrashException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.*;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarExistsInCarMaintenanceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CarAlreadyExistsInRentalCarException;

// spring stuff - annotations and data stuff
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

// requests and utilities - the boring but necessary stuff
import in.lakshay.rentACarBackend.business.requests.carRequests.CreateCarRequest;
import in.lakshay.rentACarBackend.business.requests.carRequests.DeleteCarRequest;
import in.lakshay.rentACarBackend.business.requests.carRequests.UpdateCarRequest;
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.CarDao;
import in.lakshay.rentACarBackend.entities.concretes.Car;

@Service // business logic implementation for cars
public class CarManager implements CarService {

	// dependencies - holy moly there's a lot of them!
	private final CarDao carDao; // data access
	private final ModelMapperService modelMapperService; // for dto conversion
	private final BrandService brandService; // brand validation
	private final ColorService colorService; // color validation
	private final RentalCarService rentalCarService; // rental checks
	private final CarMaintenanceService carMaintenanceService; // maintenance checks
	private final CarCrashService carCrashService; // crash history

	// constructor injection - way better than field injection
	@Autowired
	public CarManager(CarDao carDao,
				  ModelMapperService modelMapperService,
				  @Lazy BrandService brandService, // lazy to avoid circular deps
				  @Lazy ColorService colorService,
				  RentalCarService rentalCarService,
				  @Lazy CarMaintenanceService carMaintenanceService,
				  @Lazy CarCrashService carCrashService) {
		// init all the things - so many dependencies!
		this.carDao = carDao;
		this.brandService = brandService;
		this.colorService = colorService;
		this.rentalCarService = rentalCarService;
		this.carMaintenanceService = carMaintenanceService;
		this.carCrashService = carCrashService;
		this.modelMapperService = modelMapperService;

		// TODO: add logging here? would be nice for debugging
		// also maybe add some metrics?
	}


	// get all cars - no filtering, just everything
	@Override
	public DataResult<List<CarListDto>> getAll() {
		// fetch from db - get everything
		List<Car> cars = this.carDao.findAll();

		// map to DTOs - java streams are neat but kinda verbose
		List<CarListDto> carsDto = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());

		// wrap in success response with msg
		return new SuccessDataResult<>(carsDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}


	// add a new car to the system - pretty straightforward
	@Override
	public Result add(CreateCarRequest createCarRequest) throws ModelYearAfterThisYearException, BrandNotFoundException, ColorNotFoundException {
		// validations first - gotta check everything
		this.brandService.checkIsExistsByBrandId(createCarRequest.getBrandId());
		this.colorService.checkIsExistsByColorId(createCarRequest.getColorId());
		checkIsModelYearBeforeThisYear(createCarRequest.getModelYear()); // can't add future cars!

		// convert request to entity using mapper
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);

		// save to db - easy peasy
		this.carDao.save(car);

		// return success msg
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
	}

	// update existing car - change details
	@Override
	public Result update(UpdateCarRequest updateCarRequest) throws ModelYearAfterThisYearException, BrandNotFoundException, CarNotFoundException, ColorNotFoundException {
		// validations - lots of them! gotta check everything
		checkIsExistsByCarId(updateCarRequest.getCarId()); // does car exist?
		this.brandService.checkIsExistsByBrandId(updateCarRequest.getBrandId()); // does brand exist?
		this.colorService.checkIsExistsByColorId(updateCarRequest.getColorId()); // does color exist?
		checkIsModelYearBeforeThisYear(updateCarRequest.getModelYear()); // is model year valid?

		// map and save - pretty simple once validation passes
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car); // this handles both insert and update - neat!

		// return success with id
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateCarRequest.getCarId());
	}


	// delete a car - with lots of validation checks
	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) throws CarExistsInCarCrashException, CarExistsInCarMaintenanceException, CarNotFoundException, CarAlreadyExistsInRentalCarException {
		// check if car exists first
		checkIsExistsByCarId(deleteCarRequest.getCarId());

		// make sure car isn't being used anywhere - cant delete if in use
		this.rentalCarService.checkIsNotExistsByRentalCar_CarId(deleteCarRequest.getCarId());
		this.carMaintenanceService.checkIsExistsByCar_CarId(deleteCarRequest.getCarId());
		this.carCrashService.checkIfNotExistsCarCrashByCar_CarId(deleteCarRequest.getCarId());

		// all good, delete it - bye bye car!
		this.carDao.deleteById(deleteCarRequest.getCarId());

		// return success msg with id
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteCarRequest.getCarId());
	}


	// update car's kilometer reading - used when car is returned from rental
	@Override
	public void updateKilometer(int carId, int kilometer) throws ReturnKilometerLessThanRentKilometerException, CarNotFoundException {
		// validations - basic stuff
		checkIsExistsByCarId(carId); // make sure car exists
		Car car = this.carDao.getById(carId);
		checkIfReturnKilometerValid(car.getKilometer(), kilometer); // can't go backwards! physics doesn't work that way

		// update and save - simple
		car.setKilometer(kilometer);
		this.carDao.save(car); // persist changes
	}

	// get car by id
	@Override
	public DataResult<GetCarDto> getById(int id) throws CarNotFoundException {
		// check if exists
		checkIsExistsByCarId(id);

		// get from db
		Car car = this.carDao.getById(id);

		// map to dto
		GetCarDto carDto = this.modelMapperService.forDto().map(car, GetCarDto.class);

		return new SuccessDataResult<>(carDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}

	// get cars by brand id
	@Override
	public DataResult<List<CarListByBrandIdDto>> getAllByCar_BrandId(int brandId) throws BrandNotFoundException {
		// check if brand exists
		this.brandService.checkIsExistsByBrandId(brandId);

		// get cars with this brand
		List<Car> cars = this.carDao.getAllByBrand_BrandId(brandId);

		// map to dtos
		List<CarListByBrandIdDto> result = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListByBrandIdDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(result, BusinessMessages.CarMessages.CAR_LISTED_BY_BRAND_ID + brandId);
	}

	// get cars by color id
	@Override
	public DataResult<List<CarListByColorIdDto>> getAllByCar_ColorId(int colorId) throws ColorNotFoundException {
		// validate color exists
		this.colorService.checkIsExistsByColorId(colorId);

		// get matching cars
		List<Car> cars = this.carDao.getAllByColor_ColorId(colorId);

		// map to dtos
		List<CarListByColorIdDto> result = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListByColorIdDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(result, BusinessMessages.CarMessages.CAR_LISTED_BY_COLOR_ID + colorId);
	}

	// find cars by price - budget filter
	@Override
	public DataResult<List<CarListByDailyPriceDto>> findByDailyPriceLessThenEqual(double dailyPrice) {
		// note: typo in method name (then vs than) but can't change it now

		// get cars under budget
		List<Car> cars = this.carDao.findByDailyPriceLessThanEqual(dailyPrice);

		// map to dtos
		List<CarListByDailyPriceDto> response = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListByDailyPriceDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(response, BusinessMessages.CarMessages.CAR_LISTED_BY_LESS_THEN_EQUAL + dailyPrice);
	}

	// pagination support
	@Override
	public DataResult<List<CarPagedDto>> getAllPagedCar(int pageNo, int pageSize) throws CarNotFoundException {
		// validate pagination params
		checkIfPageNoAndPageSizeValid(pageNo, pageSize);

		// create pageable and get page
		Pageable pageable = PageRequest.of(pageNo-1, pageSize); // -1 because pages are 0-indexed in Spring
		List<Car> cars = this.carDao.findAll(pageable).getContent();

		// map to dtos
		List<CarPagedDto> result = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarPagedDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(result, BusinessMessages.CarMessages.ALL_CARS_PAGED);
	}

	// sorting support
	@Override
	public DataResult<List<CarSortedDto>> getAllSortedCar(int sort) {
		// get sort direction
		Sort sortList = selectSortedType(sort); // helper method to determine sort direction

		// get sorted cars
		List<Car> cars = this.carDao.findAll(sortList);

		// map to dtos
		List<CarSortedDto> result = cars.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarSortedDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(result, BusinessMessages.CarMessages.ALL_CARS_SORTED);
	}


	/* Helper methods below */

	// check if model year is valid (not in future)
	private void checkIsModelYearBeforeThisYear(int modelYear) throws ModelYearAfterThisYearException {
		if(modelYear > LocalDate.now().getYear()){
			// can't add cars from the future!
			throw new ModelYearAfterThisYearException(BusinessMessages.CarMessages.MODEL_YEAR_CANNOT_AFTER_TODAY + modelYear);
		}
	}

	// make sure return kilometer is higher than when rented
	private void checkIfReturnKilometerValid(int beforeKilometer, int afterKilometer) throws ReturnKilometerLessThanRentKilometerException {
		if(beforeKilometer > afterKilometer){ // cars can't go backwards!
			throw new ReturnKilometerLessThanRentKilometerException(BusinessMessages.CarMessages.DELIVERED_KILOMETER_CANNOT_LESS_THAN_RENTED_KILOMETER);
		}
	}

	// validate pagination params
	private void checkIfPageNoAndPageSizeValid(int pageNo, int pageSize) throws CarNotFoundException {
		if(pageNo <= 0 || pageSize <= 0) { // both must be positive
			throw new CarNotFoundException(BusinessMessages.CarMessages.PAGE_NO_OR_PAGE_SIZE_NOT_VALID);
			// weird to use CarNotFoundException here but whatever
		}
	}

	// check if car exists by id
	@Override
	public void checkIsExistsByCarId(int carId) throws CarNotFoundException {
		if(!this.carDao.existsByCarId(carId)) {
			// car not found - throw exception
			throw new CarNotFoundException(BusinessMessages.CarMessages.CAR_ID_NOT_FOUND + carId);
		}
		// otherwise all good
	}

	// check if brand is used by any cars (for brand deletion)
	@Override
	public void checkIsNotExistsByCar_BrandId(int brandId) throws BrandExistsInCarException {
		// if brand is used by any car, can't delete it
		if(this.carDao.existsByBrand_BrandId(brandId)) {
			throw new BrandExistsInCarException(BusinessMessages.CarMessages.BRAND_ID_ALREADY_EXISTS_IN_THE_CAR_TABLE + brandId);
		}
	}

	// check if color is used by any cars (for color deletion)
	@Override
	public void checkIsNotExistsByCar_ColorId(int colorId) throws ColorExistsInCarException {
		// if color is used by any car, can't delete it
		if(this.carDao.existsByColor_ColorId(colorId)) {
			throw new ColorExistsInCarException(BusinessMessages.CarMessages.COLOR_ID_ALREADY_EXISTS_IN_THE_CAR_TABLE + colorId);
		}
	}

	// get car's daily price - used for rental calculations
	@Override
	public double getDailyPriceByCarId(int carId) {
		// no validation here? should probably check if car exists first
		Car car = this.carDao.getById(carId);
		return car.getDailyPrice();
	}

	// helper to determine sort direction
	Sort selectSortedType(int sort) {
		// could use enum instead of magic numbers...
		if(sort==1) {
			return Sort.by(Sort.Direction.ASC, "dailyPrice"); // low to high
		} else if(sort==0) {
			return Sort.by(Sort.Direction.DESC, "dailyPrice"); // high to low
		} else {
			// default to desc if invalid value
			return Sort.by(Sort.Direction.DESC, "dailyPrice");
		}
	}

	// TODO: add search by name functionality

}
