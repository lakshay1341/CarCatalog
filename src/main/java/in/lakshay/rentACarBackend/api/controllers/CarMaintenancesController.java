package in.lakshay.rentACarBackend.api.controllers;

// service layer
import in.lakshay.rentACarBackend.business.abstracts.CarMaintenanceService;

// dtos for data transfer
import in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.lists.CarMaintenanceListByCarIdDto;
import in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.lists.CarMaintenanceListDto;
import in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.gets.GetCarMaintenanceDto;

// request objects
import in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests.DeleteCarMaintenanceRequest;
import in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;

// exceptions - omg so many of these!
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.CarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarAlreadyInMaintenanceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarMaintenanceNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.MaintenanceReturnDateBeforeTodayException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CarAlreadyRentedEnteredDateException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.StartDateBeforeTodayException;

// result wrappers
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

// spring stuff
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

// controller for tracking car maintenance records
// keeps track of when cars are in the shop and unavailable for rental
@RestController
@RequestMapping("/api/carMaintenances") // endpoint for maintenance data
public class CarMaintenancesController {

    private final CarMaintenanceService carMaintenanceService; // service dependency

    @Autowired // spring DI
    public CarMaintenancesController(CarMaintenanceService carMaintenanceService){
        this.carMaintenanceService = carMaintenanceService; // save reference
    }



    // get all maintenance records
    @GetMapping("/getAll")
    public DataResult<List<CarMaintenanceListDto>> getAll() {
        // just delegate to service
        return this.carMaintenanceService.getAll();
    }

    // add new maintenance record
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) throws CarNotFoundException, MaintenanceReturnDateBeforeTodayException, CarAlreadyInMaintenanceException, StartDateBeforeTodayException, CarAlreadyRentedEnteredDateException {
        // holy exceptions batman! this is getting ridiculous
        return this.carMaintenanceService.add(createCarMaintenanceRequest);
    }

    // update maintenance record
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws CarNotFoundException, MaintenanceReturnDateBeforeTodayException, CarAlreadyInMaintenanceException, CarMaintenanceNotFoundException, StartDateBeforeTodayException, CarAlreadyRentedEnteredDateException {
        // this method signature is getting out of hand... need to refactor someday
        return this.carMaintenanceService.update(updateCarMaintenanceRequest);
    }

    // delete maintenance record
    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws CarMaintenanceNotFoundException {
        // finally a method with just one exception!
        return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
    }

    // get maintenance by id
    @GetMapping("/getByCarMaintenanceId") // kinda verbose name
    public DataResult<GetCarMaintenanceDto> getByCarMaintenanceId(@RequestParam int carMaintenanceId) throws CarMaintenanceNotFoundException {
        // simple lookup
        return this.carMaintenanceService.getById(carMaintenanceId);
    }

    // get maintenance history for a specific car
    @GetMapping("/getAllByCarMaintenance_CarId") // naming could be better
    public DataResult<List<CarMaintenanceListByCarIdDto>> getAllByCarMaintenance_CarId(@RequestParam int carId) throws CarNotFoundException {
        // useful for showing maintenance history to potential buyers/renters
        return this.carMaintenanceService.getAllByCarMaintenance_CarId(carId);
    }

    // TODO: add date range filtering? would be useful

}
