package in.lakshay.rentACarBackend.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.ColorExistsInCarException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions.ColorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class ColorManager implements ColorService{
	
	private final ColorDao colorDao;
	private final CarService carService;
	private final ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService, CarService carService) {
		this.colorDao = colorDao;
		this.carService = carService;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ColorListDto>> getAll() {
		
		List<Color> colors = this.colorDao.findAll();

		List<ColorListDto> result = colors.stream().map(color -> this.modelMapperService.forDto().map(color, ColorListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) throws ColorAlreadyExistsException {
		
		checkIsNotExistsByColorName(createColorRequest.getColorName());
		
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);

		this.colorDao.save(color);

		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws ColorAlreadyExistsException, ColorNotFoundException {

		checkIsExistsByColorId(updateColorRequest.getColorId());
		checkIsNotExistsByColorName(updateColorRequest.getColorName());
		
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);

		this.colorDao.save(color);

		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateColorRequest.getColorId());
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws ColorExistsInCarException, ColorNotFoundException {

		checkIsExistsByColorId(deleteColorRequest.getColorId());
		this.carService.checkIsNotExistsByCar_ColorId(deleteColorRequest.getColorId());
		
		this.colorDao.deleteById(deleteColorRequest.getColorId());

		return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteColorRequest.getColorId());
	}

	@Override
	public DataResult<GetColorDto> getById(int id) throws ColorNotFoundException {

		checkIsExistsByColorId(id);
		
		Color color = this.colorDao.getById(id);

		GetColorDto getColorDto = this.modelMapperService.forDto().map(color, GetColorDto.class);

		return new SuccessDataResult<>(getColorDto, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + id);
	}
	
	public void checkIsExistsByColorId(int brandId) throws ColorNotFoundException {
		if(!this.colorDao.existsByColorId(brandId)) {
			throw new ColorNotFoundException(BusinessMessages.ColorMessages.COLOR_ID_NOT_FOUND + brandId);
		}
	}

	private void checkIsNotExistsByColorName(String brandName) throws ColorAlreadyExistsException {
		if(this.colorDao.existsByColorName(brandName)) {
			throw new ColorAlreadyExistsException(BusinessMessages.ColorMessages.COLOR_NAME_ALREADY_EXISTS + brandName);
		}
	}

}
