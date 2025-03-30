package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CityAlreadyExistsException extends BusinessException {

    public CityAlreadyExistsException(String message) {
        super(message);
    }
}
