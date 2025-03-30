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

public interface ColorService {
	
	DataResult<List<ColorListDto>> getAll();
	
	Result add(CreateColorRequest createColorRequest) throws ColorAlreadyExistsException;
	Result update(UpdateColorRequest updateColorRequest) throws ColorAlreadyExistsException, ColorNotFoundException;
	Result delete(DeleteColorRequest deleteColorRequest) throws ColorExistsInCarException, ColorNotFoundException;
	
	DataResult<GetColorDto> getById(int colorId) throws ColorNotFoundException;

	void checkIsExistsByColorId(int colorId) throws ColorNotFoundException;
}
