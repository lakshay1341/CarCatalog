package in.lakshay.rentACarBackend.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

// messages and exceptions
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.BrandExistsInCarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// imports for brand management - all the stuff we need
import in.lakshay.rentACarBackend.business.abstracts.BrandService;
import in.lakshay.rentACarBackend.business.abstracts.CarService;
import in.lakshay.rentACarBackend.business.dtos.brandDtos.lists.BrandListDto;
import in.lakshay.rentACarBackend.business.dtos.brandDtos.gets.GetBrandDto;
import in.lakshay.rentACarBackend.business.requests.brandRequests.CreateBrandRequest;
import in.lakshay.rentACarBackend.business.requests.brandRequests.DeleteBrandRequest;
import in.lakshay.rentACarBackend.business.requests.brandRequests.UpdateBrandRequest;
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.BrandDao;
import in.lakshay.rentACarBackend.entities.concretes.Brand;

@Service  // handles brand business logic - pretty simple service
public class BrandManager implements BrandService {

	private final BrandDao brandDao;  // data access for brands - db operations
	private final CarService carService;  // need this to check if brand is used by cars before delete
	private final ModelMapperService modelMapperService; // for entity<->dto mapping

	@Autowired // dependency injection
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService, CarService carService) {
		// save references to injected services
		this.brandDao = brandDao; // for db access
		this.carService = carService; // for validation
		this.modelMapperService = modelMapperService; // for mapping between entities and dtos
	}


	// gets all brands from db - no filtering
	@Override
	public DataResult<List<BrandListDto>> getAll() {
		// grab all brands from db - simple query
		List<Brand> brands = this.brandDao.findAll();

		// convert to DTOs using streams - kinda fancy but works well
		// probably won't have thousands of brands so this is fine
		List<BrandListDto> result = brands.stream().map(brand -> this.modelMapperService.forDto().map(brand, BrandListDto.class))
				.collect(Collectors.toList());

		// wrap in success response with msg
		return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}

	// adds a new brand to the system
	@Override
	public Result add(CreateBrandRequest createBrandRequest) throws BrandAlreadyExistsException {

		// make sure brand name doesn't already exist - can't have dupes
		checkIsNotExistByBrandName(createBrandRequest.getBrandName());

		// convert request to entity using mapper
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);

		// save to db - easy peasy
		this.brandDao.save(brand);

		// return success msg
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
	}


	// updates existing brand - change name
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws BrandAlreadyExistsException, BrandNotFoundException {
		// gotta check if brand exists and new name doesn't conflict
		// need to validate both conditions
		checkIsExistsByBrandId(updateBrandRequest.getBrandId()); // does it exist?
		checkIsNotExistByBrandName(updateBrandRequest.getBrandName()); // is name available?

		// map request to entity
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);

		// save changes - jpa is smart enuf to update existing record
		this.brandDao.save(brand); // this handles both insert and update

		// return success with id
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateBrandRequest.getBrandId());
	}


	// deletes a brand if not used by any cars
	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) throws BrandNotFoundException, BrandExistsInCarException {
		// can't delete if brand doesn't exist or is used by cars
		// gotta check both conditions
		checkIsExistsByBrandId(deleteBrandRequest.getBrandId()); // make sure it exists
		this.carService.checkIsNotExistsByCar_BrandId(deleteBrandRequest.getBrandId()); // can't delete if used by cars - would break FK constraints

		// ok to delete now - bye bye brand!
		this.brandDao.deleteById(deleteBrandRequest.getBrandId());

		// return success with id
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteBrandRequest.getBrandId());
	}

	// gets brand by id - lookup single brand
	@Override
	public DataResult<GetBrandDto> getById(int id) throws BrandNotFoundException {
		// make sure brand exists first - don't want 404s
		checkIsExistsByBrandId(id);

		// get it from db - simple lookup
		Brand brand = this.brandDao.getById(id);

		// convert to dto for client consumption
		GetBrandDto getBrandDto = this.modelMapperService.forDto().map(brand, GetBrandDto.class);

		// return with success msg
		return new SuccessDataResult<>(getBrandDto, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + id);
	}


	// helper methods below - used for validation

	// checks if brand exists by id - throws if not found
	public void checkIsExistsByBrandId(int id) throws BrandNotFoundException {
		if(!this.brandDao.existsByBrandId(id)) {
			// brand not found - throw exception with id
			throw new BrandNotFoundException(BusinessMessages.BrandMessages.BRAND_ID_NOT_FOUND + id); // brand not found!
		}
		// if we get here, brand exists
	}


	// makes sure brand name is unique in system
	public void checkIsNotExistByBrandName(String name) throws BrandAlreadyExistsException {
		if(this.brandDao.existsByBrandName(name)) {
			// oops, name already taken - can't have dupes
			throw new BrandAlreadyExistsException(BusinessMessages.BrandMessages.BRAND_NAME_ALREADY_EXISTS + name);
		}
		// if we get here, name is available
	}

	// todo: maybe add method to get most popular brands?
	// todo: add brand search by name?

}
