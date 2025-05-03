package in.lakshay.rentACarBackend.business.concretes;

import in.lakshay.rentACarBackend.business.abstracts.CityService;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.dtos.cityDtos.lists.CityListDto;
import in.lakshay.rentACarBackend.business.dtos.cityDtos.gets.GetCityDto;
import in.lakshay.rentACarBackend.business.requests.citiesRequests.CreateCityRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.CityDao;
import in.lakshay.rentACarBackend.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// city mgmt - pretty simple stuff
@Service
public class CityManager implements CityService {

    private final CityDao cityDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    // get all cities in the system
    @Override
    public DataResult<List<CityListDto>> getAll() {
        // get all cities from db
        List<City> cities = this.cityDao.findAll();

        // convert to dtos - same pattern as everywhere else
        List<CityListDto> result = cities.stream().map(city -> this.modelMapperService.forDto().map(city, CityListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    // add a new city
    @Override
    public Result add(CreateCityRequest createCityRequest) throws CityAlreadyExistsException {

        checkIsNotExistByCityName(createCityRequest.getCityName()); // no dupes plz

        // map to entity
        City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
        city.setCityId(0); // make sure id is 0 so db assigns new one

        this.cityDao.save(city); // save it

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    // get city by id
    @Override
    public DataResult<GetCityDto> getByCityId(int cityId) throws CityNotFoundException {

        checkIfExistsByCityId(cityId); // throws if not found

        // get the city
        City city = this.cityDao.getById(cityId);

        // convert to dto
        GetCityDto result = this.modelMapperService.forDto().map(city, GetCityDto.class);

        return new SuccessDataResult<>(result,BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + cityId);
    }

    // helper to check if city exists
    public void checkIfExistsByCityId(int cityId) throws CityNotFoundException {
        if(!this.cityDao.existsByCityId(cityId)){
            throw new CityNotFoundException(BusinessMessages.CityMessages.CITY_ID_NOT_FOUND + cityId); // not found!
        }
    }

    // make sure we dont have duplicate city names
    private void checkIsNotExistByCityName(String cityName) throws CityAlreadyExistsException {
        if(this.cityDao.existsByCityName(cityName)) {
            throw new CityAlreadyExistsException(BusinessMessages.CityMessages.CITY_NAME_ALREADY_EXISTS + cityName); // already exists!
        }
        // all good if we get here
    }

}
