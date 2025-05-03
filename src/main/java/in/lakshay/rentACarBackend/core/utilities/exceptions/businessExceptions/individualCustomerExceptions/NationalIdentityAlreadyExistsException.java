package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when someone tries to register with a national ID that's already in the system
// cuz we can't have duplicate national IDs - that would be a mess!
public class NationalIdentityAlreadyExistsException extends BusinessException {

    // simple constructor
    public NationalIdentityAlreadyExistsException(String message) {
        super(message);  // pass to parent
    }

    // maybe add a version that masks part of the ID in the error?
    // for privacy reasons we shouldn't show the full ID in logs
}
