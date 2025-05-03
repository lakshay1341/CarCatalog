package in.lakshay.rentACarBackend.business.abstracts;

import in.lakshay.rentACarBackend.business.dtos.cityDtos.lists.CityListDto;
import in.lakshay.rentACarBackend.business.dtos.cityDtos.gets.GetCityDto;
import in.lakshay.rentACarBackend.business.requests.citiesRequests.CreateCityRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

import java.util.List;

// service for city stuff
// pretty basic for now - just add/list
// will prob need more features later
public interface CityService {

    // get all cities
    DataResult<List<CityListDto>> getAll();

    // add new city
    Result add(CreateCityRequest createCityRequest) throws CityAlreadyExistsException;

    // get city by id
    DataResult<GetCityDto> getByCityId(int cityId) throws CityNotFoundException;

    // check if exists - helper for other services
    void checkIfExistsByCityId(int cityId) throws CityNotFoundException;

    // TODO: need to add update/delete
    // TODO: maybe add filtering by country?
    // TODO: add sorting options?

}
