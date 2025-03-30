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

@Service
public class CityManager implements CityService {

    private final CityDao cityDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
        this.cityDao = cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CityListDto>> getAll() {

        List<City> cities = this.cityDao.findAll();

        List<CityListDto> result = cities.stream().map(city -> this.modelMapperService.forDto().map(city, CityListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(CreateCityRequest createCityRequest) throws CityAlreadyExistsException {

        checkIsNotExistByCityName(createCityRequest.getCityName());

        City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
        city.setCityId(0);

        this.cityDao.save(city);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    @Override
    public DataResult<GetCityDto> getByCityId(int cityId) throws CityNotFoundException {

        checkIfExistsByCityId(cityId);

        City city = this.cityDao.getById(cityId);

        GetCityDto result = this.modelMapperService.forDto().map(city, GetCityDto.class);

        return new SuccessDataResult<>(result,BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + cityId);
    }

    public void checkIfExistsByCityId(int cityId) throws CityNotFoundException {
        if(!this.cityDao.existsByCityId(cityId)){
            throw new CityNotFoundException(BusinessMessages.CityMessages.CITY_ID_NOT_FOUND + cityId);
        }
    }

    private void checkIsNotExistByCityName(String cityName) throws CityAlreadyExistsException {
        if(this.cityDao.existsByCityName(cityName)) {
            throw new CityAlreadyExistsException(BusinessMessages.CityMessages.CITY_NAME_ALREADY_EXISTS + cityName);
        }
    }

}
