package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class AdditionalQuantityNotValidException extends BusinessException {

    public AdditionalQuantityNotValidException(String message) {
        super(message);
    }
}
