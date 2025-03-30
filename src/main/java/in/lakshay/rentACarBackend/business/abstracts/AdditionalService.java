package in.lakshay.rentACarBackend.business.abstracts;

import in.lakshay.rentACarBackend.business.dtos.additionalDtos.lists.AdditionalListDto;
import in.lakshay.rentACarBackend.business.dtos.additionalDtos.gets.GetAdditionalDto;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.DeleteAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.CreateAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.UpdateAdditionalRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.AdditionalAlreadyExistsInOrderedAdditionalException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

import java.util.List;

public interface AdditionalService {

    DataResult<List<AdditionalListDto>> getAll();

    Result add(CreateAdditionalRequest createAdditionalRequest) throws AdditionalAlreadyExistsException;
    Result update(UpdateAdditionalRequest updateAdditionalRequest) throws AdditionalAlreadyExistsException, AdditionalNotFoundException;
    Result delete(DeleteAdditionalRequest deleteAdditionalRequest) throws AdditionalNotFoundException, AdditionalAlreadyExistsInOrderedAdditionalException;

    DataResult<GetAdditionalDto> getByAdditionalId(int additionalId) throws AdditionalNotFoundException;

    void checkIfExistsByAdditionalId(int additionalId) throws AdditionalNotFoundException;
    
}
