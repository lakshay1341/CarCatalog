package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class BrandAlreadyExistsException extends BusinessException {

    public BrandAlreadyExistsException(String message) {
        super(message);
    }
}
