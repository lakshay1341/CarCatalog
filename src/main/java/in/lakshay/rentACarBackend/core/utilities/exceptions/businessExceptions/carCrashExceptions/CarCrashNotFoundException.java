package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CarCrashNotFoundException extends BusinessException {

    public CarCrashNotFoundException(String message) {
        super(message);
    }
}
