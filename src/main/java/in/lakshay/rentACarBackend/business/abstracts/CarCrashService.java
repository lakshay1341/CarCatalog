package in.lakshay.rentACarBackend.business.abstracts;

import in.lakshay.rentACarBackend.business.dtos.carCrashDtos.gets.GetCarCrashDto;
import in.lakshay.rentACarBackend.business.dtos.carCrashDtos.lists.CarCrashListByCarIdDto;
import in.lakshay.rentACarBackend.business.dtos.carCrashDtos.lists.CarCrashListDto;
import in.lakshay.rentACarBackend.business.requests.carCrashRequests.CreateCarCrashRequest;
import in.lakshay.rentACarBackend.business.requests.carCrashRequests.DeleteCarCrashRequest;
import in.lakshay.rentACarBackend.business.requests.carCrashRequests.UpdateCarCrashRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions.CarCrashNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions.CarExistsInCarCrashException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions.CrashDateAfterTodayException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.CarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

import java.util.List;

// interface for handling car crash operations
// todo: maybe add more methods for reporting later?
public interface CarCrashService {

    // get all crash records
    DataResult<List<CarCrashListDto>> getAll();

    // crud operations
    Result add(CreateCarCrashRequest createCarCrashRequest) throws CrashDateAfterTodayException, CarNotFoundException;
    Result update(UpdateCarCrashRequest updateCarCrashRequest) throws CrashDateAfterTodayException, CarCrashNotFoundException, CarNotFoundException; // so many exceptions lol
    Result delete(DeleteCarCrashRequest deleteCarCrashRequest) throws CarCrashNotFoundException;

    // get specific crash by id
    DataResult<GetCarCrashDto> getById(int carCrashId) throws CarCrashNotFoundException;

    // get all crashes for a specific car
    DataResult<List<CarCrashListByCarIdDto>> getCarCrashByCar_CarId(int carId) throws CarNotFoundException;

    // check if car has any crashes - used when deleting cars
    void checkIfNotExistsCarCrashByCar_CarId(int carId) throws CarExistsInCarCrashException;

}
