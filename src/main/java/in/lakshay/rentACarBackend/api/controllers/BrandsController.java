package in.lakshay.rentACarBackend.api.controllers;

import java.util.List;

import jakarta.validation.Valid;

// exception imports - need these for validation
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions.BrandNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.BrandExistsInCarException;

// spring annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// all the brand stuff we need for the controller
import in.lakshay.rentACarBackend.business.abstracts.BrandService;
import in.lakshay.rentACarBackend.business.dtos.brandDtos.lists.BrandListDto;
import in.lakshay.rentACarBackend.business.dtos.brandDtos.gets.GetBrandDto;
import in.lakshay.rentACarBackend.business.requests.brandRequests.CreateBrandRequest;
import in.lakshay.rentACarBackend.business.requests.brandRequests.DeleteBrandRequest;
import in.lakshay.rentACarBackend.business.requests.brandRequests.UpdateBrandRequest;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

@RestController
@RequestMapping("/api/brands") // endpoint for brand operations
public class BrandsController {
	// handles brand CRUD operations
	// pretty simple controller compared to some others

	private final BrandService brandService;  // service to handle brand logic

	@Autowired // dependency injection
	public BrandsController(BrandService brandService) {
		this.brandService = brandService; // save reference
	}


	// gets all brands - pretty straightforward
	@GetMapping("/getAll") // GET /api/brands/getAll
	public DataResult<List<BrandListDto>> getAll(){
		// nothing fancy, just get everything
		return this.brandService.getAll(); // no filtering here
	}

	@PostMapping("/add")  // adds a new brand
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) throws BrandAlreadyExistsException {
		// just pass to service layer
		// validation happens in service
		return this.brandService.add(createBrandRequest);
	}

	@PutMapping("/update") // updates existing brand
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) throws BrandAlreadyExistsException, BrandNotFoundException {
		// gotta check if it exists first
		return this.brandService.update(updateBrandRequest); // service handles validation
	}

	// deletes a brand if not used by any cars
	@DeleteMapping("/delete") // DELETE /api/brands/delete
	public Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest) throws BrandNotFoundException, BrandExistsInCarException {
		// cant delete brands used by cars - would break referential integrity
		return this.brandService.delete(deleteBrandRequest); // todo: add cascade delete option?
	}

	@GetMapping("/getById") // get single brand
	public DataResult<GetBrandDto> getById(@RequestParam int brandId) throws BrandNotFoundException {
		// find brand by id - simple lookup
		return this.brandService.getById(brandId); // throws if not found
	}

	// todo: maybe add search by name?

}
