package in.lakshay.rentACarBackend.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

// messages and exceptions - need these for validation
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.ColorExistsInCarException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// services and dtos - the usual imports
import in.lakshay.rentACarBackend.business.abstracts.CarService;
import in.lakshay.rentACarBackend.business.abstracts.ColorService;
import in.lakshay.rentACarBackend.business.dtos.colorDtos.lists.ColorListDto;
import in.lakshay.rentACarBackend.business.dtos.colorDtos.gets.GetColorDto;
import in.lakshay.rentACarBackend.business.requests.colorRequests.CreateColorRequest;
import in.lakshay.rentACarBackend.business.requests.colorRequests.DeleteColorRequest;
import in.lakshay.rentACarBackend.business.requests.colorRequests.UpdateColorRequest;
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.ColorDao;
import in.lakshay.rentACarBackend.entities.concretes.Color;

@Service // handles color business logic - pretty simple service
public class ColorManager implements ColorService{
	// manages all the color operations - CRUD for colors
	// not too complex compared to some other services

	private final ColorDao colorDao; // data access layer - for db operations
	private final CarService carService; // need to check if color is used by cars before delete
	private final ModelMapperService modelMapperService; // for entity<->dto mapping

	@Autowired // dependency injection
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService, CarService carService) {
		// save references to injected services
		this.colorDao = colorDao; // for db access
		this.carService = carService; // for validation
		this.modelMapperService = modelMapperService; // for mapping
	}


	// gets all colors from db - no filtering
	@Override
	public DataResult<List<ColorListDto>> getAll() {
		// fetch all colors - simple query
		List<Color> colors = this.colorDao.findAll();

		// map to dtos - streams r cool but kinda overkill for small lists tbh
		// probably won't have thousands of colors lol
		List<ColorListDto> result = colors.stream().map(color -> this.modelMapperService.forDto().map(color, ColorListDto.class))
				.collect(Collectors.toList());

		// wrap in success response with msg
		return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}

	// adds a new color to the system
	@Override
	public Result add(CreateColorRequest createColorRequest) throws ColorAlreadyExistsException {
		// check if color name already exists - can't have dupes
		checkIsNotExistsByColorName(createColorRequest.getColorName());

		// convert request to entity using mapper
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);

		// save to db - easy peasy
		this.colorDao.save(color);

		// return success msg
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
	}


	// updates existing color - change name
	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws ColorAlreadyExistsException, ColorNotFoundException {
		// validations - color must exist and new name must be unique
		// gotta check both conditions
		checkIsExistsByColorId(updateColorRequest.getColorId()); // does it exist?
		checkIsNotExistsByColorName(updateColorRequest.getColorName()); // is name available?

		// map request to entity
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);

		// save changes - jpa is smart enuf to update existing record
		this.colorDao.save(color);

		// return success with id
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateColorRequest.getColorId());
	}


	// deletes a color if not used by any cars
	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws ColorExistsInCarException, ColorNotFoundException {
		// validations - gotta check these
		checkIsExistsByColorId(deleteColorRequest.getColorId()); // make sure it exists
		this.carService.checkIsNotExistsByCar_ColorId(deleteColorRequest.getColorId()); // can't delete if used by cars - would break FK constraints

		// delete from db - bye bye color!
		this.colorDao.deleteById(deleteColorRequest.getColorId());

		// return success with id
		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteColorRequest.getColorId());
	}

	// gets color by id - lookup single color
	@Override
	public DataResult<GetColorDto> getById(int id) throws ColorNotFoundException {
		// check if exists first - don't want 404s
		checkIsExistsByColorId(id);

		// get from db - simple lookup
		Color color = this.colorDao.getById(id);

		// convert to dto for client consumption
		GetColorDto getColorDto = this.modelMapperService.forDto().map(color, GetColorDto.class);

		// return with success msg
		return new SuccessDataResult<>(getColorDto, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + id);
	}


	// helper method - checks if color exists by id
	public void checkIsExistsByColorId(int brandId) throws ColorNotFoundException {
		// note: param should be colorId not brandId - oops! but it works anyway
		if(!this.colorDao.existsByColorId(brandId)) {
			// oops not found - throw exception
			throw new ColorNotFoundException(BusinessMessages.ColorMessages.COLOR_ID_NOT_FOUND + brandId);
		}
		// if we get here, color exists
	}

	// helper method - checks if color name is unique in system
	private void checkIsNotExistsByColorName(String brandName) throws ColorAlreadyExistsException {
		// another param name mistake - should be colorName not brandName
		if(this.colorDao.existsByColorName(brandName)) {
			// name already taken :( - can't have dupes
			throw new ColorAlreadyExistsException(BusinessMessages.ColorMessages.COLOR_NAME_ALREADY_EXISTS + brandName);
		}
		// if we get here, name is available
	}

	// todo: maybe add a method to get colors by popularity?
	// todo: add method to get most used colors?
	// todo: add color search by name?

}
