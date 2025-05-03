package in.lakshay.rentACarBackend.business.abstracts;

import in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.lists.CarMaintenanceListByCarIdDto;
import in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.lists.CarMaintenanceListDto;
import in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.gets.GetCarMaintenanceDto;
import in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests.DeleteCarMaintenanceRequest;
import in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.CarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarAlreadyInMaintenanceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarExistsInCarMaintenanceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarMaintenanceNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.MaintenanceReturnDateBeforeTodayException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CarAlreadyRentedEnteredDateException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.StartDateBeforeTodayException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

import java.time.LocalDate;
import java.util.List;

// service interface for car maintenance operations
// tracks when cars are in the shop and unavailable for rental
// todo: maybe add reporting methods later?
public interface CarMaintenanceService {

	// get all maintenance records
	DataResult<List<CarMaintenanceListDto>> getAll();

	// crud operations
	// sooo many exceptions lol
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws MaintenanceReturnDateBeforeTodayException, CarAlreadyInMaintenanceException, CarNotFoundException, CarAlreadyRentedEnteredDateException, StartDateBeforeTodayException;
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws CarMaintenanceNotFoundException, MaintenanceReturnDateBeforeTodayException, CarAlreadyInMaintenanceException, CarNotFoundException, CarAlreadyRentedEnteredDateException, StartDateBeforeTodayException;
	Result delete(DeleteCarMaintenanceRequest carMaintenanceRequest) throws CarMaintenanceNotFoundException;

	// get specific maintenance record
	DataResult<GetCarMaintenanceDto> getById(int carMaintenanceId) throws CarMaintenanceNotFoundException;

	// get maintenance history for a specific car
	DataResult<List<CarMaintenanceListByCarIdDto>> getAllByCarMaintenance_CarId(int carId) throws CarNotFoundException;

	// validation methods - used by other services too
	// check if car is already in maintenance on a specific date
	void checkIfNotCarAlreadyInMaintenanceOnTheEnteredDate(int carId, LocalDate enteredDate) throws CarAlreadyInMaintenanceException;

	// check if car has any maintenance records - used when deleting cars
	void checkIsExistsByCar_CarId(int carId) throws CarExistsInCarMaintenanceException;

}
