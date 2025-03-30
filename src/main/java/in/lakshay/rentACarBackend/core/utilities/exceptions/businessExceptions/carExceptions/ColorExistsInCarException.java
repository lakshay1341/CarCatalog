package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class ColorExistsInCarException extends BusinessException {

    public ColorExistsInCarException(String message) {
        super(message);
    }
}
