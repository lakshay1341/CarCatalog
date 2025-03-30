package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class BrandExistsInCarException extends BusinessException {

    public BrandExistsInCarException(String message) {
        super(message);
    }
}
