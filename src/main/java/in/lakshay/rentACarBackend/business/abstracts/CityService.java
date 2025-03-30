package in.lakshay.rentACarBackend.business.abstracts;

import in.lakshay.rentACarBackend.business.dtos.cityDtos.lists.CityListDto;
import in.lakshay.rentACarBackend.business.dtos.cityDtos.gets.GetCityDto;
import in.lakshay.rentACarBackend.business.requests.citiesRequests.CreateCityRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

import java.util.List;

public interface CityService {

    DataResult<List<CityListDto>> getAll();

    Result add(CreateCityRequest createCityRequest) throws CityAlreadyExistsException;

    DataResult<GetCityDto> getByCityId(int cityId) throws CityNotFoundException;

    void checkIfExistsByCityId(int cityId) throws CityNotFoundException;

}
