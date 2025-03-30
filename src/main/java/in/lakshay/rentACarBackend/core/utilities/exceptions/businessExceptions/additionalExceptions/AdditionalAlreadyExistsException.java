package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class AdditionalAlreadyExistsException extends BusinessException {

    public AdditionalAlreadyExistsException(String message) {
        super(message);
    }
}
