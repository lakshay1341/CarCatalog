package in.lakshay.rentACarBackend.business.concretes;

import in.lakshay.rentACarBackend.business.abstracts.CarCrashService;
import in.lakshay.rentACarBackend.business.abstracts.CarService;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
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
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.CarCrashDao;
import in.lakshay.rentACarBackend.entities.concretes.CarCrash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

// handles all the car crash related stuff - accidents happen ¯\_(ツ)_/¯
@Service
public class CarCrashManager implements CarCrashService {

    private final CarCrashDao carCrashDao;
    private final CarService carService;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CarCrashManager(CarCrashDao carCrashDao, CarService carService, ModelMapperService modelMapperService) {
        this.carCrashDao = carCrashDao;
        this.carService = carService;
        this.modelMapperService = modelMapperService;
    }


    // gets all car crashes from db
    @Override
    public DataResult<List<CarCrashListDto>> getAll() {
        // grab all crashes
        List<CarCrash> carCrashes = this.carCrashDao.findAll();

        // map to dtos - streams r cool but kinda verbose
        List<CarCrashListDto> result = carCrashes.stream().map(carCrash -> this.modelMapperService.forDto().map(carCrash, CarCrashListDto.class))
                .collect(Collectors.toList());
        // need to manually set car ids cuz mapper doesnt do it right
        for(int i=0;i<carCrashes.size();i++){
            result.get(i).setCarId(carCrashes.get(i).getCar().getCarId());
        }

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(CreateCarCrashRequest createCarCrashRequest) throws CrashDateAfterTodayException, CarNotFoundException {

        // make sure crash date isnt in the future (unless time travel?)
        checkIfCrashDateBeforeToday(createCarCrashRequest.getCrashDate());
        this.carService.checkIsExistsByCarId(createCarCrashRequest.getCarId());

        // convert to entity
        CarCrash carCrash = this.modelMapperService.forRequest().map(createCarCrashRequest, CarCrash.class);

        // save to db
        this.carCrashDao.save(carCrash);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }


    // update existing crash record
    @Override
    public Result update(UpdateCarCrashRequest updateCarCrashRequest) throws CrashDateAfterTodayException, CarCrashNotFoundException, CarNotFoundException {

        checkIfExistsByCarCrashId(updateCarCrashRequest.getCarCrashId()); // make sure it exists
        checkIfCrashDateBeforeToday(updateCarCrashRequest.getCrashDate()); // no future crashes plz
        this.carService.checkIsExistsByCarId(updateCarCrashRequest.getCarId());

        CarCrash carCrash = this.modelMapperService.forRequest().map(updateCarCrashRequest, CarCrash.class);

        this.carCrashDao.save(carCrash); // save it

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateCarCrashRequest.getCarCrashId());
    }

    @Override
    public Result delete(DeleteCarCrashRequest deleteCarCrashRequest) throws CarCrashNotFoundException {

        checkIfExistsByCarCrashId(deleteCarCrashRequest.getCarCrashId());

        this.carCrashDao.deleteById(deleteCarCrashRequest.getCarCrashId());

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteCarCrashRequest.getCarCrashId());
    }

    @Override
    public DataResult<GetCarCrashDto> getById(int carCrashId) throws CarCrashNotFoundException {

        checkIfExistsByCarCrashId(carCrashId);

        CarCrash carCrash = this.carCrashDao.getById(carCrashId);

        GetCarCrashDto result = this.modelMapperService.forDto().map(carCrash, GetCarCrashDto.class);
        result.setCarId(carCrash.getCar().getCarId());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + carCrashId);
    }

    // get crash history for a specific car
    @Override
    public DataResult<List<CarCrashListByCarIdDto>> getCarCrashByCar_CarId(int carId) throws CarNotFoundException {

        this.carService.checkIsExistsByCarId(carId);  // car exists?

        // get all crashes for this car
        List<CarCrash> carCrashes = this.carCrashDao.getAllByCar_CarId(carId);

        // map to dtos - this is getting repetitive but whatevs
        List<CarCrashListByCarIdDto> result = carCrashes.stream().map(carCrash -> this.modelMapperService.forDto()
                .map(carCrash, CarCrashListByCarIdDto.class)).collect(Collectors.toList());
        for(int i=0;i<carCrashes.size();i++){
            result.get(i).setCarId(carCrashes.get(i).getCar().getCarId()); // fix ids
        }

        return new SuccessDataResult<>(result, BusinessMessages.CarCrashMessages.CAR_CRASH_LISTED_BY_CAR_ID + carId);
    }


    // helper to check if crash record exists
    private void checkIfExistsByCarCrashId(int carCrashId) throws CarCrashNotFoundException {
        if(!this.carCrashDao.existsByCarCrashId(carCrashId)){
            throw new CarCrashNotFoundException(BusinessMessages.CarCrashMessages.CAR_CRASH_ID_NOT_FOUND + carCrashId);
        }
    }

    // cant have crashes in the future! unless we got time machines lol
    private void checkIfCrashDateBeforeToday(LocalDate crashDate) throws CrashDateAfterTodayException {
        if(crashDate.isAfter(LocalDate.now())){
            throw new CrashDateAfterTodayException(BusinessMessages.CarCrashMessages.CRASH_DATE_CANNOT_AFTER_TODAY + crashDate);
        }
    }

    @Override
    public void checkIfNotExistsCarCrashByCar_CarId(int carId) throws CarExistsInCarCrashException {
        if(this.carCrashDao.existsByCar_CarId(carId)){
            throw new CarExistsInCarCrashException(BusinessMessages.CarCrashMessages.CAR_ID_ALREADY_EXISTS_IN_THE_CAR_CRASH_TABLE + carId);
        }
    }
}
