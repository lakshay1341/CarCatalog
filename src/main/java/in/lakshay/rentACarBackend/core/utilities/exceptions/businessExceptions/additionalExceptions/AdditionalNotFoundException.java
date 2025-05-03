package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when we cant find the additional service requested
// usually means someone tried to access with wrong ID
public class AdditionalNotFoundException extends BusinessException {

    public AdditionalNotFoundException(String message) {
        super(message);  // pass to parent class
    }

    // might need to add more stuff here later if we need special handling
}
