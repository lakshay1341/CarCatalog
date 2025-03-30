package in.lakshay.rentACarBackend.business.concretes;

import in.lakshay.rentACarBackend.business.abstracts.AdditionalService;
import in.lakshay.rentACarBackend.business.abstracts.OrderedAdditionalService;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.dtos.additionalDtos.lists.AdditionalListDto;
import in.lakshay.rentACarBackend.business.dtos.additionalDtos.gets.GetAdditionalDto;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.CreateAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.DeleteAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.UpdateAdditionalRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.AdditionalAlreadyExistsInOrderedAdditionalException;
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.AdditionalDao;
import in.lakshay.rentACarBackend.entities.concretes.Additional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalManager implements AdditionalService {

    private final AdditionalDao additionalDao;
    private final OrderedAdditionalService orderedAdditionalService;
    private final ModelMapperService modelMapperService;

    @Autowired
    public AdditionalManager(AdditionalDao additionalDao, ModelMapperService modelMapperService, @Lazy OrderedAdditionalService orderedAdditionalService) {
        this.additionalDao = additionalDao;
        this.orderedAdditionalService = orderedAdditionalService;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public DataResult<List<AdditionalListDto>> getAll() {

        List<Additional> additionalList = this.additionalDao.findAll();

        List<AdditionalListDto> result = additionalList.stream().map(additional -> this.modelMapperService.forDto().map(additional, AdditionalListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(CreateAdditionalRequest createAdditionalRequest) throws AdditionalAlreadyExistsException {

        checkIsNotExistsByAdditionalName(createAdditionalRequest.getAdditionalName());

        Additional additional = this.modelMapperService.forRequest().map(createAdditionalRequest, Additional.class);

        this.additionalDao.save(additional);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateAdditionalRequest updateAdditionalRequest) throws AdditionalAlreadyExistsException, AdditionalNotFoundException {

        checkIfExistsByAdditionalId(updateAdditionalRequest.getAdditionalId());
        checkIsNotExistsByAdditionalName(updateAdditionalRequest.getAdditionalName());

        Additional additional = this.modelMapperService.forRequest().map(updateAdditionalRequest, Additional.class);

        this.additionalDao.save(additional);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateAdditionalRequest.getAdditionalId());
    }

    @Override
    public Result delete(DeleteAdditionalRequest deleteAdditionalRequest) throws AdditionalNotFoundException, AdditionalAlreadyExistsInOrderedAdditionalException {

        checkIfExistsByAdditionalId(deleteAdditionalRequest.getAdditionalId());
        this.orderedAdditionalService.checkIsNotExistsByOrderedAdditional_AdditionalId(deleteAdditionalRequest.getAdditionalId());

        this.additionalDao.deleteById(deleteAdditionalRequest.getAdditionalId());

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteAdditionalRequest.getAdditionalId());
    }

    @Override
    public DataResult<GetAdditionalDto> getByAdditionalId(int additionalId) throws AdditionalNotFoundException {

        checkIfExistsByAdditionalId(additionalId);

        Additional addition = this.additionalDao.getById(additionalId);

        GetAdditionalDto result = this.modelMapperService.forDto().map(addition, GetAdditionalDto.class);

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + additionalId);
    }

    @Override
    public void checkIfExistsByAdditionalId(int additionalId) throws AdditionalNotFoundException {
        if(!this.additionalDao.existsByAdditionalId(additionalId)){
            throw new AdditionalNotFoundException(BusinessMessages.AdditionalMessages.ADDITIONAL_ID_NOT_FOUND + additionalId);
        }
    }

    private void checkIsNotExistsByAdditionalName(String additionalName) throws AdditionalAlreadyExistsException {
        if(this.additionalDao.existsByAdditionalName(additionalName)){
            throw new AdditionalAlreadyExistsException(BusinessMessages.AdditionalMessages.ADDITIONAL_NAME_ALREADY_EXISTS + additionalName);
        }
    }

}
