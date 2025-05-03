package in.lakshay.rentACarBackend.business.abstracts;

import java.util.List;

import in.lakshay.rentACarBackend.business.dtos.carDtos.gets.GetCarDto;
import in.lakshay.rentACarBackend.business.dtos.carDtos.lists.*;
import in.lakshay.rentACarBackend.business.requests.carRequests.CreateCarRequest;
import in.lakshay.rentACarBackend.business.requests.carRequests.DeleteCarRequest;
import in.lakshay.rentACarBackend.business.requests.carRequests.UpdateCarRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions.CarExistsInCarCrashException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.*;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarExistsInCarMaintenanceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CarAlreadyExistsInRentalCarException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

// service interface for car operations
// this is the main business logic for cars
// implemented by CarManager
public interface CarService {

	// get all cars - basic listing
	DataResult<List<CarListDto>> getAll();

	// CRUD operations
	// add a new car - throws exceptions if data invalid
	Result add(CreateCarRequest createCarRequest) throws ModelYearAfterThisYearException, BrandNotFoundException, ColorNotFoundException;

	// update existing car - lots of validation here
	Result update(UpdateCarRequest updateCarRequest) throws ModelYearAfterThisYearException, BrandNotFoundException, CarNotFoundException, ColorNotFoundException;

	// delete car - checks for dependencies first
	Result delete(DeleteCarRequest deleteCarRequest) throws CarExistsInCarCrashException, CarExistsInCarMaintenanceException, CarNotFoundException, CarAlreadyExistsInRentalCarException;

	// update car's kilometer reading - used when car is returned
	// throws exception if new reading < old reading (can't go backwards!)
	void updateKilometer(int carId, int kilometer) throws ReturnKilometerLessThanRentKilometerException, CarNotFoundException;

	// search/filter operations
	// find cars cheaper than specified price
	DataResult<List<CarListByDailyPriceDto>> findByDailyPriceLessThenEqual(double dailyPrice);

	// pagination support - get page of cars
	DataResult<List<CarPagedDto>> getAllPagedCar(int pageNo, int pageSize) throws CarNotFoundException;

	// sorting support - sort=1 for asc, sort=2 for desc (i think?)
	DataResult<List<CarSortedDto>> getAllSortedCar(int sort);

	// get single car by id
	DataResult<GetCarDto> getById(int id) throws CarNotFoundException;

	// filter by brand
	DataResult<List<CarListByBrandIdDto>> getAllByCar_BrandId(int brandId) throws BrandNotFoundException;

	// filter by color
	DataResult<List<CarListByColorIdDto>> getAllByCar_ColorId(int colorId) throws ColorNotFoundException;

	// validation helpers - used internally and by other services
	// check if car exists
	void checkIsExistsByCarId(int carId) throws CarNotFoundException;

	// check if brand is used by any cars
	void checkIsNotExistsByCar_BrandId(int brandId) throws BrandExistsInCarException;

	// check if color is used by any cars
	void checkIsNotExistsByCar_ColorId(int colorId) throws ColorExistsInCarException;

	// utility - get price for a car
	// todo: maybe add discount logic here someday?
	double getDailyPriceByCarId(int carId);
}
