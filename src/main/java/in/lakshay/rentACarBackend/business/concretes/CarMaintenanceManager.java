package in.lakshay.rentACarBackend.business.concretes;

// so many imports... java needs a better module system
import in.lakshay.rentACarBackend.business.abstracts.CarMaintenanceService;
import in.lakshay.rentACarBackend.business.abstracts.CarService;
import in.lakshay.rentACarBackend.business.abstracts.RentalCarService;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.lists.CarMaintenanceListByCarIdDto;
import in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.lists.CarMaintenanceListDto;
import in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.gets.GetCarMaintenanceDto;
import in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests.DeleteCarMaintenanceRequest;
import in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;

// exception imports - way too many of these
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.CarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarAlreadyInMaintenanceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarExistsInCarMaintenanceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarMaintenanceNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.MaintenanceReturnDateBeforeTodayException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CarAlreadyRentedEnteredDateException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.StartDateBeforeTodayException;

// utility imports
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.CarMaintenanceDao;
import in.lakshay.rentACarBackend.entities.concretes.CarMaintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

// handles car maintenance records - when cars go to shop etc
@Service
public class CarMaintenanceManager implements CarMaintenanceService {

    // dependencies
    private final CarMaintenanceDao carMaintenanceDao;  // data access
    private final CarService carService;  // need to check car exists
    private final RentalCarService rentalCarService;  // check if car is rented
    private final ModelMapperService modelMapperService;  // dto mapping

    // constructor w/ dependency injection
    @Autowired
    public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, CarService carService,
            RentalCarService rentalCarService, ModelMapperService modelMapperService){
        this.carMaintenanceDao = carMaintenanceDao;
        this.carService = carService;
        this.rentalCarService = rentalCarService;
        this.modelMapperService = modelMapperService;
    }


    // get all maintenance records
    @Override
    public DataResult<List<CarMaintenanceListDto>> getAll() {
        // grab all records from db
        List<CarMaintenance> carMaintenances = this.carMaintenanceDao.findAll();

        // map to dtos - streams make this nicer but still kinda verbose
        List<CarMaintenanceListDto> result = carMaintenances.stream()
                .map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    // add new maintenance record
    @Override
    public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws MaintenanceReturnDateBeforeTodayException,
            CarAlreadyInMaintenanceException, CarNotFoundException, CarAlreadyRentedEnteredDateException, StartDateBeforeTodayException {
        // omg so many exceptions to handle

        // run all validations first
        checkAllValidationForCarMaintenanceForAdd(createCarMaintenanceRequest.getReturnDate(), createCarMaintenanceRequest.getCarId());

        // convert to entity
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
        carMaintenance.setMaintenanceId(0);  // ensure new record

        // save to db
        this.carMaintenanceDao.save(carMaintenance);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    // update existing maintenance record
    @Override
    public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws CarMaintenanceNotFoundException,
            MaintenanceReturnDateBeforeTodayException, CarAlreadyInMaintenanceException, CarNotFoundException,
            CarAlreadyRentedEnteredDateException, StartDateBeforeTodayException {
        // so many exceptions... java verbosity at its finest

        // check record exists
        checkIsExistsByCarMaintenanceId(updateCarMaintenanceRequest.getMaintenanceId());

        // run all validations
        checkAllValidationForCarMaintenanceForUpdate(updateCarMaintenanceRequest.getReturnDate(),
                updateCarMaintenanceRequest.getCarId(), updateCarMaintenanceRequest.getMaintenanceId());

        // convert to entity
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);

        // save changes
        this.carMaintenanceDao.save(carMaintenance);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateCarMaintenanceRequest.getMaintenanceId());
    }

    // delete a maintenance record
    @Override
    public Result delete(DeleteCarMaintenanceRequest carMaintenanceRequest) throws CarMaintenanceNotFoundException {
        // make sure it exists first
        checkIsExistsByCarMaintenanceId(carMaintenanceRequest.getMaintenanceId());

        // delete it
        this.carMaintenanceDao.deleteById(carMaintenanceRequest.getMaintenanceId());

        // success msg with id
        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + carMaintenanceRequest.getMaintenanceId());
    }

    // get a specific maintenance record by id
    @Override
    public DataResult<GetCarMaintenanceDto> getById(int carMaintenanceId) throws CarMaintenanceNotFoundException {
        // check exists
        checkIsExistsByCarMaintenanceId(carMaintenanceId);

        // get from db
        CarMaintenance carMaintenance = this.carMaintenanceDao.getById(carMaintenanceId);

        // map to dto
        GetCarMaintenanceDto result = this.modelMapperService.forDto().map(carMaintenance, GetCarMaintenanceDto.class);

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + carMaintenanceId);
    }

    // get maintenance history for a specific car
    @Override
    public DataResult<List<CarMaintenanceListByCarIdDto>> getAllByCarMaintenance_CarId(int carId) throws CarNotFoundException {
        // make sure car exists
        this.carService.checkIsExistsByCarId(carId);

        // get all maintenance records for this car
        List<CarMaintenance> carMaintenances = this.carMaintenanceDao.findAllByCar_CarId(carId);

        // map to dtos - same pattern as usual
        List<CarMaintenanceListByCarIdDto> result = carMaintenances.stream()
                .map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListByCarIdDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.CarMaintenanceMessages.CAR_MAINTENANCE_LISTED_BY_CAR_ID + carId);
    }

    // run all validations for adding a new maintenance record
    // lots of checks to make sure everything is valid
    private void checkAllValidationForCarMaintenanceForAdd(LocalDate returnDate, int carId) throws CarAlreadyInMaintenanceException,
            MaintenanceReturnDateBeforeTodayException, CarNotFoundException, CarAlreadyRentedEnteredDateException, StartDateBeforeTodayException {

        // return date must be in future
        checkIfNotReturnDateBeforeToday(returnDate);

        // car must exist
        this.carService.checkIsExistsByCarId(carId);

        // car not already in maintenance
        checkIfNotCarAlreadyInMaintenanceOnTheEnteredDate(carId, LocalDate.now());

        // car not already rented for these dates
        this.rentalCarService.checkIfNotCarAlreadyRentedEnteredDate(carId, LocalDate.now()); // today
        this.rentalCarService.checkIfNotCarAlreadyRentedEnteredDate(carId, returnDate); // return date
        this.rentalCarService.checkIfNotCarAlreadyRentedBetweenStartAndFinishDates(carId, LocalDate.now(), returnDate); // period
    }

    // similar to add validation but for updates
    // need to exclude the current record from checks
    private void checkAllValidationForCarMaintenanceForUpdate(LocalDate returnDate, int carId, int maintenanceId) throws CarAlreadyInMaintenanceException,
            MaintenanceReturnDateBeforeTodayException, CarNotFoundException, CarAlreadyRentedEnteredDateException, StartDateBeforeTodayException {

        // date validation
        checkIfNotReturnDateBeforeToday(returnDate);

        // car exists
        this.carService.checkIsExistsByCarId(carId);

        // not in maintenance (excluding this record)
        checkIfNotCarAlreadyInMaintenanceOnTheEnteredDateForUpdate(carId, LocalDate.now(), maintenanceId);

        // not rented during this period
        this.rentalCarService.checkIfNotCarAlreadyRentedEnteredDate(carId, LocalDate.now());
        this.rentalCarService.checkIfNotCarAlreadyRentedEnteredDate(carId, returnDate);
        this.rentalCarService.checkIfNotCarAlreadyRentedBetweenStartAndFinishDates(carId, LocalDate.now(), returnDate);
    }

    // make sure return date is in the future
    // can't schedule maintenance to end in the past!
    private void checkIfNotReturnDateBeforeToday(LocalDate returnDate) throws MaintenanceReturnDateBeforeTodayException {
        if(returnDate != null){
            if(returnDate.isBefore(LocalDate.now())){
                // oops - date is in the past
                throw new MaintenanceReturnDateBeforeTodayException(BusinessMessages.CarMaintenanceMessages.RETURN_DATE_CANNOT_BEFORE_TODAY + returnDate);
            }
        }
        // null dates are ok - means indefinite maintenance
    }

    // check if car is already in maintenance on a given date
    @Override
    public void checkIfNotCarAlreadyInMaintenanceOnTheEnteredDate(int carId, LocalDate enteredDate) throws CarAlreadyInMaintenanceException {
        // get all maintenance records for this car
        List<CarMaintenance> carMaintenances = this.carMaintenanceDao.findAllByCar_CarId(carId);

        if(carMaintenances != null){
            // check each record
            for (CarMaintenance carMaintenance : carMaintenances){
                // null return date means indefinite maintenance
                if (carMaintenance.getReturnDate()==null){
                    // FIXME: this looks wrong - null.now() will NPE
                    // assume 14 days from now if null
                    if(carMaintenance.getReturnDate().now().plusDays(14).isAfter(enteredDate)){
                        throw new CarAlreadyInMaintenanceException(BusinessMessages.CarMaintenanceMessages.CAR_ALREADY_IN_MAINTENANCE);
                    }
                }else if(carMaintenance.getReturnDate().isAfter(enteredDate)){
                    // car still in maintenance on the entered date
                    throw new CarAlreadyInMaintenanceException(BusinessMessages.CarMaintenanceMessages.CAR_ALREADY_IN_MAINTENANCE);
                }
            }
        }
    }

    // similar to above but for updates - need to exclude current record
    public void checkIfNotCarAlreadyInMaintenanceOnTheEnteredDateForUpdate(int carId, LocalDate enteredDate, int maintenanceId) throws CarAlreadyInMaintenanceException {
        // get all maintenance records for this car
        List<CarMaintenance> carMaintenances = this.carMaintenanceDao.findAllByCar_CarId(carId);

        if(carMaintenances != null){
            for (CarMaintenance carMaintenance : carMaintenances){
                // skip the record we're updating
                if(carMaintenance.getMaintenanceId() == maintenanceId){
                    continue;  // don't check against self
                }

                // same logic as above method
                if (carMaintenance.getReturnDate()==null){
                    // FIXME: same NPE issue as above
                    if(carMaintenance.getReturnDate().now().plusDays(14).isAfter(enteredDate)){
                        throw new CarAlreadyInMaintenanceException(BusinessMessages.CarMaintenanceMessages.CAR_ALREADY_IN_MAINTENANCE);
                    }
                }else if(carMaintenance.getReturnDate().isAfter(enteredDate)){
                    throw new CarAlreadyInMaintenanceException(BusinessMessages.CarMaintenanceMessages.CAR_ALREADY_IN_MAINTENANCE);
                }
            }
        }
    }

    // check if maintenance record exists
    private void checkIsExistsByCarMaintenanceId(int carMaintenanceId) throws CarMaintenanceNotFoundException {
        if(!this.carMaintenanceDao.existsByMaintenanceId(carMaintenanceId)){
            // not found - throw exception
            throw new CarMaintenanceNotFoundException(BusinessMessages.CarMaintenanceMessages.CAR_MAINTENANCE_ID_NOT_FOUND + carMaintenanceId);
        }
        // exists - all good
    }

    // check if car has any maintenance records
    // used when trying to delete a car
    @Override
    public void checkIsExistsByCar_CarId(int carId) throws CarExistsInCarMaintenanceException {
        if(this.carMaintenanceDao.existsByCar_CarId(carId)){
            // car has maintenance records - can't delete
            throw new CarExistsInCarMaintenanceException(BusinessMessages.CarMaintenanceMessages.CAR_ID_ALREADY_EXISTS_IN_THE_CAR_MAINTENANCE_TABLE + carId);
        }
        // no records - safe to delete
    }

}
