package in.lakshay.rentACarBackend.api.controllers;

// service layer
import in.lakshay.rentACarBackend.business.abstracts.CityService;

// dtos for data transfer
import in.lakshay.rentACarBackend.business.dtos.cityDtos.lists.CityListDto;
import in.lakshay.rentACarBackend.business.dtos.cityDtos.gets.GetCityDto;

// request objects
import in.lakshay.rentACarBackend.business.requests.citiesRequests.CreateCityRequest;

// exceptions
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityNotFoundException;

// result wrappers
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

// spring stuff
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

// controller for city operations
// handles endpoints for city CRUD operations
@RestController
@RequestMapping("/api/cities") // endpoint for cities
public class CitiesController {

    private final CityService cityService; // service dependency

    @Autowired // spring DI
    public CitiesController(CityService cityService) {
        this.cityService = cityService; // save reference
    }



    // get all cities in the system
    @GetMapping("/getAll")
    public DataResult<List<CityListDto>> getAll(){
        // just delegate to service layer
        return this.cityService.getAll();
    }

    // add a new city to the system
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) throws CityAlreadyExistsException {
        // todo: add better validation msgs
        return this.cityService.add(createCityRequest);
    }

    // get city by id
    @GetMapping("getByCityId") // missing slash before path - oops
    public DataResult<GetCityDto> getByCityId(@RequestParam int cityId) throws CityNotFoundException {
        // simple lookup
        return this.cityService.getByCityId(cityId);
    }

    // TODO: maybe add search by name endpoint?

}
