package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// exception thrown when someone tries to add an additional that already exists
// happens when name is duplicate
public class AdditionalAlreadyExistsException extends BusinessException {

    public AdditionalAlreadyExistsException(String message) {
        super(message);  // just pass msg to parent, nothing fancy here
    }

}
