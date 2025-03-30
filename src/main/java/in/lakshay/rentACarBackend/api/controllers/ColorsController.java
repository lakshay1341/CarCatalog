package in.lakshay.rentACarBackend.api.controllers;

import java.util.List;

import jakarta.validation.Valid;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.ColorExistsInCarException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.lakshay.rentACarBackend.business.abstracts.ColorService;
import in.lakshay.rentACarBackend.business.dtos.colorDtos.lists.ColorListDto;
import in.lakshay.rentACarBackend.business.dtos.colorDtos.gets.GetColorDto;
import in.lakshay.rentACarBackend.business.requests.colorRequests.CreateColorRequest;
import in.lakshay.rentACarBackend.business.requests.colorRequests.DeleteColorRequest;
import in.lakshay.rentACarBackend.business.requests.colorRequests.UpdateColorRequest;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
	
	private final ColorService colorService;
	
	@Autowired
	public ColorsController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	
	@GetMapping("/getAll")
	public DataResult<List<ColorListDto>> getAll(){
		return this.colorService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) throws ColorAlreadyExistsException {
		return this.colorService.add(createColorRequest);
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) throws ColorNotFoundException, ColorAlreadyExistsException {
		return this.colorService.update(updateColorRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) throws ColorNotFoundException, ColorExistsInCarException {
		return this.colorService.delete(deleteColorRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<GetColorDto> getById(@RequestParam int colorId) throws ColorNotFoundException {
		return this.colorService.getById(colorId);
	}

}
