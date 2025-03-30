package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class BrandNotFoundException extends BusinessException {

    public BrandNotFoundException(String message) {
        super(message);
    }
}
