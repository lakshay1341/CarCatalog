package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class OrderedAdditionalAlreadyExistsException extends BusinessException {

    public OrderedAdditionalAlreadyExistsException(String message) {
        super(message);
    }
}
