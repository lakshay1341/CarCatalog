package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class ColorNotFoundException extends BusinessException {

    public ColorNotFoundException(String message) {
        super(message);
    }
}
