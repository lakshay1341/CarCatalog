package in.lakshay.rentACarBackend.api.controllers;

// service layer
import in.lakshay.rentACarBackend.business.abstracts.CarCrashService;

// dtos for data transfer
import in.lakshay.rentACarBackend.business.dtos.carCrashDtos.gets.GetCarCrashDto;
import in.lakshay.rentACarBackend.business.dtos.carCrashDtos.lists.CarCrashListByCarIdDto;
import in.lakshay.rentACarBackend.business.dtos.carCrashDtos.lists.CarCrashListDto;

// request objects
import in.lakshay.rentACarBackend.business.requests.carCrashRequests.CreateCarCrashRequest;
import in.lakshay.rentACarBackend.business.requests.carCrashRequests.DeleteCarCrashRequest;
import in.lakshay.rentACarBackend.business.requests.carCrashRequests.UpdateCarCrashRequest;

// exceptions - need a lot of these for crash handling
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions.CarCrashNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions.CrashDateAfterTodayException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.CarNotFoundException;

// result wrappers
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

// spring annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

// controller for handling car crash records
// keeps track of accidents for insurance/history purposes
@RestController
@RequestMapping("/api/carCrashes") // endpoint for crash data
public class CarCrashesController {

    private final CarCrashService carCrashService;  // service dependency

    @Autowired  // spring DI
    public CarCrashesController(CarCrashService carCrashService) {
        this.carCrashService = carCrashService;  // save reference
    }



    // get all crash records - probably not great for large datasets
    @GetMapping("/getAll")
    public DataResult<List<CarCrashListDto>> getAll(){
        // just pass thru to service layer
        return this.carCrashService.getAll();
    }

    // add new crash record
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCarCrashRequest createCarCrashRequest) throws CrashDateAfterTodayException, CarNotFoundException {
        // cant crash a car that doesnt exist or crash in the future lol
        return this.carCrashService.add(createCarCrashRequest);
    }

    // update crash details
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCarCrashRequest updateCarCrashRequest) throws CrashDateAfterTodayException, CarCrashNotFoundException, CarNotFoundException {
        // wow thats a lot of exceptions... maybe refactor someday?
        return this.carCrashService.update(updateCarCrashRequest);
    }

    // remove crash record - prob shouldnt do this in prod
    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteCarCrashRequest deleteCarCrashRequest) throws CarCrashNotFoundException {
        // should we rly allow deleting crash records? insurance fraud? :P
        return this.carCrashService.delete(deleteCarCrashRequest);
    }

    // get crash by id
    @GetMapping("/getById")
    public DataResult<GetCarCrashDto> getById(@RequestParam int carCrashId) throws CarCrashNotFoundException {
        // simple lookup
        return this.carCrashService.getById(carCrashId);
    }

    // get crash history for a specific car
    @GetMapping("/getCarCrashByCar_CarId") // naming could be better tbh
    public DataResult<List<CarCrashListByCarIdDto>> getCarCrashByCar_CarId(@RequestParam int carId) throws CarNotFoundException {
        // useful for showing accident history to potential buyers
        return this.carCrashService.getCarCrashByCar_CarId(carId);
    }

    // TODO: maybe add date range filtering?

}
