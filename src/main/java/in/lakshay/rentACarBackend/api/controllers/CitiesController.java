package in.lakshay.rentACarBackend.api.controllers;

import in.lakshay.rentACarBackend.business.abstracts.CityService;
import in.lakshay.rentACarBackend.business.dtos.cityDtos.lists.CityListDto;
import in.lakshay.rentACarBackend.business.dtos.cityDtos.gets.GetCityDto;
import in.lakshay.rentACarBackend.business.requests.citiesRequests.CreateCityRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/getAll")
    public DataResult<List<CityListDto>> getAll(){
        return this.cityService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) throws CityAlreadyExistsException {
        return this.cityService.add(createCityRequest);
    }

    @GetMapping("getByCityId")
    public DataResult<GetCityDto> getByCityId(@RequestParam int cityId) throws CityNotFoundException {
        return this.cityService.getByCityId(cityId);
    }

}
