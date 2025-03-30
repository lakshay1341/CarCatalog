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

public interface CarCrashService {

    DataResult<List<CarCrashListDto>> getAll();

    Result add(CreateCarCrashRequest createCarCrashRequest) throws CrashDateAfterTodayException, CarNotFoundException;
    Result update(UpdateCarCrashRequest updateCarCrashRequest) throws CrashDateAfterTodayException, CarCrashNotFoundException, CarNotFoundException;
    Result delete(DeleteCarCrashRequest deleteCarCrashRequest) throws CarCrashNotFoundException;

    DataResult<GetCarCrashDto> getById(int carCrashId) throws CarCrashNotFoundException;
    DataResult<List<CarCrashListByCarIdDto>> getCarCrashByCar_CarId(int carId) throws CarNotFoundException;

    void checkIfNotExistsCarCrashByCar_CarId(int carId) throws CarExistsInCarCrashException;

}
