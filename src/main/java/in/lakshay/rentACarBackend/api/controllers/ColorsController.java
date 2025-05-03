package in.lakshay.rentACarBackend.api.controllers;

import java.util.List;

import jakarta.validation.Valid;

// exceptions
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.ColorExistsInCarException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

// spring mvc stuff
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// app imports - the usual suspects
import in.lakshay.rentACarBackend.business.abstracts.ColorService;
import in.lakshay.rentACarBackend.business.dtos.colorDtos.lists.ColorListDto;
import in.lakshay.rentACarBackend.business.dtos.colorDtos.gets.GetColorDto;
import in.lakshay.rentACarBackend.business.requests.colorRequests.CreateColorRequest;
import in.lakshay.rentACarBackend.business.requests.colorRequests.DeleteColorRequest;
import in.lakshay.rentACarBackend.business.requests.colorRequests.UpdateColorRequest;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

@RestController
@RequestMapping("/api/colors") // REST endpoint for colors
public class ColorsController {
	// handles HTTP requests for color operations
	// pretty simple stuff - just basic CRUD for car colors

	private final ColorService colorService; // service layer dependency

	@Autowired  // spring magic
	public ColorsController(ColorService colorService) {
		this.colorService = colorService; // inject the service
	}

	// TODO: add some metrics tracking?

	// get all colors - simple pass-through to service
	@GetMapping("/getAll")  // GET /api/colors/getAll
	public DataResult<List<ColorListDto>> getAll(){
		return this.colorService.getAll(); // just delegate to service
		// should probably add pagination at some point
	}

	// add a new color
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) throws ColorAlreadyExistsException {
		// validation happens via @Valid annotation
		return this.colorService.add(createColorRequest);
	}

	// update existing color
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) throws ColorNotFoundException, ColorAlreadyExistsException {
		return this.colorService.update(updateColorRequest); // service handles biz logic
	}

	// delete a color if not used by cars
	@DeleteMapping("/delete")  // DELETE /api/colors/delete
	public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) throws ColorNotFoundException, ColorExistsInCarException {
		// will throw exception if color is used by cars
		// cant delete colors that are in use - referential integrity ftw
		return this.colorService.delete(deleteColorRequest);  // delegate to service
	}

	// get color by id
	@GetMapping("/getById")  // GET /api/colors/getById?colorId=123
	public DataResult<GetColorDto> getById(@RequestParam int colorId) throws ColorNotFoundException {
		// todo: add caching here maybe?
		return this.colorService.getById(colorId);  // simple lookup
	}

	// TODO: add search by name endpoint
	// TODO: add bulk operations?

}
