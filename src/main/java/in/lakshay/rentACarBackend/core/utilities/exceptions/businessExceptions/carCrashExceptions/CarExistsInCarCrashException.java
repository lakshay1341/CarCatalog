package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CarExistsInCarCrashException extends BusinessException {

    public CarExistsInCarCrashException(String message) {
        super(message);
    }
}
