package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CityNotFoundException extends BusinessException {

    public CityNotFoundException(String message) {
        super(message);
    }
}
