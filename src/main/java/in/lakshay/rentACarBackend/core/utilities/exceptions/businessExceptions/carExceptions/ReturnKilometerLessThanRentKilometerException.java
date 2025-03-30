package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class ReturnKilometerLessThanRentKilometerException extends BusinessException {

    public ReturnKilometerLessThanRentKilometerException(String message) {
        super(message);
    }
}
