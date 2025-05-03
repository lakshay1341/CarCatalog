package in.lakshay.rentACarBackend.business.abstracts;

// imports for extra stuff like gps, baby seats etc
// these are the add-ons customers can rent with their cars
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

import java.util.List;  // for collections

// service for extras like GPS, baby seats, etc
// these are the add-ons customers can rent with their cars
// implemented by AdditionalManager
public interface AdditionalService {

    // get all the extras we offer - no filtering
    // used by frontend to show available add-ons
    DataResult<List<AdditionalListDto>> getAll();

    // crud stuff - pretty standard operations
    // add new add-on type - checks if name already exists
    Result add(CreateAdditionalRequest createAdditionalRequest) throws AdditionalAlreadyExistsException;  // add new one

    // update existing add-on - checks if exists and if new name is available
    Result update(UpdateAdditionalRequest updateAdditionalRequest) throws AdditionalAlreadyExistsException, AdditionalNotFoundException;  // change existing

    // delete add-on - checks if it's used by any rentals first
    // can't delete if it's already ordered by someone
    Result delete(DeleteAdditionalRequest deleteAdditionalRequest) throws AdditionalNotFoundException, AdditionalAlreadyExistsInOrderedAdditionalException;  // remove it

    // find by id - get single add-on details
    DataResult<GetAdditionalDto> getByAdditionalId(int additionalId) throws AdditionalNotFoundException;

    // helper to check if exists - used by other services
    // throws if not found - cleaner than returning boolean
    void checkIfExistsByAdditionalId(int additionalId) throws AdditionalNotFoundException;  // other services use this

    // TODO: maybe add search by name? might be useful later
    // TODO: add sorting by popularity?
    // TODO: add filtering by price range?
}
