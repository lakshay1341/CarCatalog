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

public interface CarService {

	DataResult<List<CarListDto>> getAll();
	
	Result add(CreateCarRequest createCarRequest) throws ModelYearAfterThisYearException, BrandNotFoundException, ColorNotFoundException;
	Result update(UpdateCarRequest updateCarRequest) throws ModelYearAfterThisYearException, BrandNotFoundException, CarNotFoundException, ColorNotFoundException;
	Result delete(DeleteCarRequest deleteCarRequest) throws CarExistsInCarCrashException, CarExistsInCarMaintenanceException, CarNotFoundException, CarAlreadyExistsInRentalCarException;

	void updateKilometer(int carId, int kilometer) throws ReturnKilometerLessThanRentKilometerException, CarNotFoundException;

	DataResult<List<CarListByDailyPriceDto>> findByDailyPriceLessThenEqual(double dailyPrice);
	DataResult<List<CarPagedDto>> getAllPagedCar(int pageNo, int pageSize) throws CarNotFoundException;
	DataResult<List<CarSortedDto>> getAllSortedCar(int sort);
	DataResult<GetCarDto> getById(int id) throws CarNotFoundException;
	DataResult<List<CarListByBrandIdDto>> getAllByCar_BrandId(int brandId) throws BrandNotFoundException;
	DataResult<List<CarListByColorIdDto>> getAllByCar_ColorId(int colorId) throws ColorNotFoundException;

	void checkIsExistsByCarId(int carId) throws CarNotFoundException;
	void checkIsNotExistsByCar_BrandId(int brandId) throws BrandExistsInCarException;
	void checkIsNotExistsByCar_ColorId(int colorId) throws ColorExistsInCarException;

	double getDailyPriceByCarId(int carId);
}
