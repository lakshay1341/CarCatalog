package in.lakshay.rentACarBackend.api.controllers;

import java.util.List;

import jakarta.validation.Valid;

// car stuff imports - way too many of these tbh
import in.lakshay.rentACarBackend.business.dtos.carDtos.lists.*;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions.CarExistsInCarCrashException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.CarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.ModelYearAfterThisYearException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarExistsInCarMaintenanceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CarAlreadyExistsInRentalCarException;

// spring stuff
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// service and dto stuff - the important bits
import in.lakshay.rentACarBackend.business.abstracts.CarService;
import in.lakshay.rentACarBackend.business.dtos.carDtos.gets.GetCarDto;
import in.lakshay.rentACarBackend.business.requests.carRequests.CreateCarRequest;
import in.lakshay.rentACarBackend.business.requests.carRequests.DeleteCarRequest;
import in.lakshay.rentACarBackend.business.requests.carRequests.UpdateCarRequest;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

@RestController
@RequestMapping("/api/cars") // cars endpoint
public class CarsController {
	// handles all the car-related HTTP requests
	// this is where all the car API magic happens

	// dependency injection
	private final CarService carService;  // service layer

	@Autowired // spring magic
	public CarsController(CarService carService) {
		this.carService = carService;  // store reference
	}

	// TODO: add some logging here maybe?
	// also should add metrics tracking

	// get all cars - no filters
	@GetMapping("/getAll")  // GET /api/cars/getAll
	public DataResult<List<CarListDto>> getAll(){
		// just pass thru to service
		return this.carService.getAll();  // nothing fancy here
	}


	// add new car to the system
	@PostMapping("/add")  // POST /api/cars/add
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) throws ColorNotFoundException, ModelYearAfterThisYearException, BrandNotFoundException {
		// todo: add better validation msgs - these exceptions are too generic
		// also need to add better error handling
		return this.carService.add(createCarRequest);  // delegate to service
	}

	// update existing car
	@PutMapping("/update")  // PUT /api/cars/update
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) throws ColorNotFoundException, CarNotFoundException, ModelYearAfterThisYearException, BrandNotFoundException {
		// so many exceptions lol
		// srsly need to refactor this at some point
		return this.carService.update(updateCarRequest);  // just pass to service
	}

	@DeleteMapping("/delete") // maybe change to path var later? would be cleaner
	public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) throws CarNotFoundException, CarAlreadyExistsInRentalCarException, CarExistsInCarMaintenanceException, CarExistsInCarCrashException {
		// omg this method signature is ridiculous
		// i think we have a record for longest method sig here lol
		return this.carService.delete(deleteCarRequest);  // delegate to service
	}


	// get single car by id
	@GetMapping("/getById")  // GET /api/cars/getById?carId=123
	public DataResult<GetCarDto> getById(@RequestParam int carId) throws CarNotFoundException {
		// should add caching here probably
		return this.carService.getById(carId);  // simple lookup
	}

	// filter by brand
	@GetMapping("/getAllByCar_BrandId")  // GET /api/cars/getAllByCar_BrandId?brandId=123
	public DataResult<List<CarListByBrandIdDto>> getAllByCar_BrandId(@RequestParam int brandId) throws BrandNotFoundException {
		return this.carService.getAllByCar_BrandId(brandId);  // filter by brand
	}

	// filter by color
	@GetMapping("/getAllByCar_ColorId")  // GET /api/cars/getAllByCar_ColorId?colorId=123
	public DataResult<List<CarListByColorIdDto>> getAllByCar_ColorId(@RequestParam int colorId) throws ColorNotFoundException {
		return this.carService.getAllByCar_ColorId(colorId);  // filter by color
	}

	// find cars cheaper than or equal to price
	@GetMapping("findByDailyPriceLessThenEqual") // typo in 'then' vs 'than' - fix later
	public DataResult<List<CarListByDailyPriceDto>> findByDailyPriceLessThenEqual(@RequestParam double dailyPrice){
		// budget filter
		// too lazy to fix the typo in the method name lol
		return this.carService.findByDailyPriceLessThenEqual(dailyPrice);  // price filter
	}

	// pagination support
	@GetMapping("getAllPagedCar")  // missing slash here but whatever
	public DataResult<List<CarPagedDto>> getAllPagedCar(@RequestParam int pageNo, int pageSize) throws CarNotFoundException {
		// should probably add some validation for page size...
		// negative page size would be bad
		return this.carService.getAllPagedCar(pageNo, pageSize);  // paginated results
	}

	// sorting support - useful for UI
	@GetMapping("getAllSortedCar")  // another missing slash, oops
	public DataResult<List<CarSortedDto>> getAllSortedCar(@RequestParam int sort){
		// sort=1 asc, sort=0 desc - should use enum instead?
		// this is kinda hacky but it works
		return this.carService.getAllSortedCar(sort);  // sorted results
	}

	// TODO: add search by name endpoint
	// TODO: add filter by price range
	// TODO: add filter by year
	// TODO: maybe add a combined search endpoint?

}
