package in.lakshay.rentACarBackend.api.controllers;

import in.lakshay.rentACarBackend.business.abstracts.CarCrashService;
import in.lakshay.rentACarBackend.business.dtos.carCrashDtos.gets.GetCarCrashDto;
import in.lakshay.rentACarBackend.business.dtos.carCrashDtos.lists.CarCrashListByCarIdDto;
import in.lakshay.rentACarBackend.business.dtos.carCrashDtos.lists.CarCrashListDto;
import in.lakshay.rentACarBackend.business.requests.carCrashRequests.CreateCarCrashRequest;
import in.lakshay.rentACarBackend.business.requests.carCrashRequests.DeleteCarCrashRequest;
import in.lakshay.rentACarBackend.business.requests.carCrashRequests.UpdateCarCrashRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions.CarCrashNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions.CrashDateAfterTodayException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.CarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/carCrashes")
public class CarCrashesController {

    private final CarCrashService carCrashService;

    @Autowired
    public CarCrashesController(CarCrashService carCrashService) {
        this.carCrashService = carCrashService;
    }


    @GetMapping("/getAll")
    public DataResult<List<CarCrashListDto>> getAll(){
        return this.carCrashService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCarCrashRequest createCarCrashRequest) throws CrashDateAfterTodayException, CarNotFoundException {
        return this.carCrashService.add(createCarCrashRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCarCrashRequest updateCarCrashRequest) throws CrashDateAfterTodayException, CarCrashNotFoundException, CarNotFoundException {
        return this.carCrashService.update(updateCarCrashRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteCarCrashRequest deleteCarCrashRequest) throws CarCrashNotFoundException {
        return this.carCrashService.delete(deleteCarCrashRequest);
    }

    @GetMapping("/getById")
    public DataResult<GetCarCrashDto> getById(@RequestParam int carCrashId) throws CarCrashNotFoundException {
        return this.carCrashService.getById(carCrashId);
    }

    @GetMapping("/getCarCrashByCar_CarId")
    public DataResult<List<CarCrashListByCarIdDto>> getCarCrashByCar_CarId(@RequestParam int carId) throws CarNotFoundException {
        return this.carCrashService.getCarCrashByCar_CarId(carId);
    }

}
