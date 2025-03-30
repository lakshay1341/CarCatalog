package in.lakshay.rentACarBackend.business.abstracts;

import java.util.List;

import in.lakshay.rentACarBackend.business.dtos.brandDtos.lists.BrandListDto;
import in.lakshay.rentACarBackend.business.dtos.brandDtos.gets.GetBrandDto;
import in.lakshay.rentACarBackend.business.requests.brandRequests.CreateBrandRequest;
import in.lakshay.rentACarBackend.business.requests.brandRequests.DeleteBrandRequest;
import in.lakshay.rentACarBackend.business.requests.brandRequests.UpdateBrandRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.BrandExistsInCarException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

public interface BrandService {
	
	DataResult<List<BrandListDto>> getAll();
	
	Result add(CreateBrandRequest createBrandRequest) throws BrandAlreadyExistsException;
	Result update(UpdateBrandRequest updateBrandRequest) throws BrandAlreadyExistsException, BrandNotFoundException;
	Result delete(DeleteBrandRequest deleteBrandRequest) throws BrandNotFoundException, BrandExistsInCarException;
	
	DataResult<GetBrandDto> getById(int colorId) throws BrandNotFoundException;
	
	void checkIsExistsByBrandId(int brandId) throws BrandNotFoundException;
	void checkIsNotExistByBrandName(String brandName) throws BrandAlreadyExistsException;

}
