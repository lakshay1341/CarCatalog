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

// handles extra services/addons for rentals - like gps, baby seats, etc
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


    // get all available extras/addons
    @Override
    public DataResult<List<AdditionalListDto>> getAll() {
        // get all from db
        List<Additional> additionalList = this.additionalDao.findAll();

        // map to dtos - same pattern as usual
        List<AdditionalListDto> result = additionalList.stream().map(additional -> this.modelMapperService.forDto().map(additional, AdditionalListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    // add a new addon option
    @Override
    public Result add(CreateAdditionalRequest createAdditionalRequest) throws AdditionalAlreadyExistsException {

        checkIsNotExistsByAdditionalName(createAdditionalRequest.getAdditionalName()); // no dupes

        // map to entity
        Additional additional = this.modelMapperService.forRequest().map(createAdditionalRequest, Additional.class);

        this.additionalDao.save(additional); // save it

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    // update existing addon
    @Override
    public Result update(UpdateAdditionalRequest updateAdditionalRequest) throws AdditionalAlreadyExistsException, AdditionalNotFoundException {

        checkIfExistsByAdditionalId(updateAdditionalRequest.getAdditionalId()); // exists?
        checkIsNotExistsByAdditionalName(updateAdditionalRequest.getAdditionalName()); // name not taken?

        // map to entity
        Additional additional = this.modelMapperService.forRequest().map(updateAdditionalRequest, Additional.class);

        this.additionalDao.save(additional); // save changes

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateAdditionalRequest.getAdditionalId());
    }

    // delete an addon - cant delete if its in use tho
    @Override
    public Result delete(DeleteAdditionalRequest deleteAdditionalRequest) throws AdditionalNotFoundException, AdditionalAlreadyExistsInOrderedAdditionalException {

        checkIfExistsByAdditionalId(deleteAdditionalRequest.getAdditionalId()); // exists?
        // make sure its not being used in any orders
        this.orderedAdditionalService.checkIsNotExistsByOrderedAdditional_AdditionalId(deleteAdditionalRequest.getAdditionalId());

        this.additionalDao.deleteById(deleteAdditionalRequest.getAdditionalId()); // delete it

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteAdditionalRequest.getAdditionalId());
    }

    @Override
    public DataResult<GetAdditionalDto> getByAdditionalId(int additionalId) throws AdditionalNotFoundException {

        checkIfExistsByAdditionalId(additionalId);

        Additional addition = this.additionalDao.getById(additionalId);

        GetAdditionalDto result = this.modelMapperService.forDto().map(addition, GetAdditionalDto.class);

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + additionalId);
    }

    // helper to check if addon exists
    @Override
    public void checkIfExistsByAdditionalId(int additionalId) throws AdditionalNotFoundException {
        if(!this.additionalDao.existsByAdditionalId(additionalId)){
            throw new AdditionalNotFoundException(BusinessMessages.AdditionalMessages.ADDITIONAL_ID_NOT_FOUND + additionalId); // not found!
        }
    }

    // make sure we dont have duplicate names
    private void checkIsNotExistsByAdditionalName(String additionalName) throws AdditionalAlreadyExistsException {
        if(this.additionalDao.existsByAdditionalName(additionalName)){
            throw new AdditionalAlreadyExistsException(BusinessMessages.AdditionalMessages.ADDITIONAL_NAME_ALREADY_EXISTS + additionalName); // already exists!
        }
        // all good if we get here
    }

}
