package in.lakshay.rentACarBackend.business.abstracts;

import java.util.List;

import in.lakshay.rentACarBackend.business.dtos.colorDtos.lists.ColorListDto;
import in.lakshay.rentACarBackend.business.dtos.colorDtos.gets.GetColorDto;
import in.lakshay.rentACarBackend.business.requests.colorRequests.CreateColorRequest;
import in.lakshay.rentACarBackend.business.requests.colorRequests.DeleteColorRequest;
import in.lakshay.rentACarBackend.business.requests.colorRequests.UpdateColorRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.ColorExistsInCarException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

// service for managing car colors
// pretty simple compared to some other services
public interface ColorService {

	// get all available colors
	DataResult<List<ColorListDto>> getAll();

	// add a new color - checks if name already exists
	Result add(CreateColorRequest createColorRequest) throws ColorAlreadyExistsException;

	// update existing color - checks if exists and if new name is available
	Result update(UpdateColorRequest updateColorRequest) throws ColorAlreadyExistsException, ColorNotFoundException;

	// delete color - checks if it's used by any cars first
	Result delete(DeleteColorRequest deleteColorRequest) throws ColorExistsInCarException, ColorNotFoundException;

	// get color by id - throws if not found
	DataResult<GetColorDto> getById(int colorId) throws ColorNotFoundException;

	// helper to check if color exists - used by other services too
	// throws exception if not found
	void checkIsExistsByColorId(int colorId) throws ColorNotFoundException;

	// todo: maybe add some color validation? hex codes or something?
}
