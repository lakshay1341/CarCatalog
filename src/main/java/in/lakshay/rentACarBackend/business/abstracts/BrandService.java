package in.lakshay.rentACarBackend.business.abstracts;

import java.util.List;

// brand-related imports
import in.lakshay.rentACarBackend.business.dtos.brandDtos.lists.BrandListDto;
import in.lakshay.rentACarBackend.business.dtos.brandDtos.gets.GetBrandDto;
import in.lakshay.rentACarBackend.business.requests.brandRequests.CreateBrandRequest;
import in.lakshay.rentACarBackend.business.requests.brandRequests.DeleteBrandRequest;
import in.lakshay.rentACarBackend.business.requests.brandRequests.UpdateBrandRequest;

// exceptions
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.BrandExistsInCarException;

// result wrappers
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

// service interface for brand operations
// implemented by BrandManager class
// handles all the business logic for car brands like Toyota, Honda etc
public interface BrandService {

	// get all brands - returns everything
	DataResult<List<BrandListDto>> getAll();  // no params needed, just get all

	// crud operations - the usual stuff
	Result add(CreateBrandRequest createBrandRequest) throws BrandAlreadyExistsException; // create new brand
	Result update(UpdateBrandRequest updateBrandRequest) throws BrandAlreadyExistsException, BrandNotFoundException; // change existing
	Result delete(DeleteBrandRequest deleteBrandRequest) throws BrandNotFoundException, BrandExistsInCarException; // remove if not used

	// get brand by id - lookup single brand
	DataResult<GetBrandDto> getById(int colorId) throws BrandNotFoundException; // oops param name should be brandId not colorId

	// validation helpers - used internally by other services mostly
	void checkIsExistsByBrandId(int brandId) throws BrandNotFoundException; // check if brand exists
	void checkIsNotExistByBrandName(String brandName) throws BrandAlreadyExistsException; // check if name is available

	// TODO: maybe add search by name later?
	// TODO: add sorting options?

}
