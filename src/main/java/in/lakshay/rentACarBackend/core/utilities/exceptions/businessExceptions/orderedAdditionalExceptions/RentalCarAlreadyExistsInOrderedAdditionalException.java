package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class RentalCarAlreadyExistsInOrderedAdditionalException extends BusinessException {

    public RentalCarAlreadyExistsInOrderedAdditionalException(String message) {
        super(message);
    }
}
