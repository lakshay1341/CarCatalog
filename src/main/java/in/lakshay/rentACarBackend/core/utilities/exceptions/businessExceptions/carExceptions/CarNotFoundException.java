package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CarNotFoundException extends BusinessException {

    public CarNotFoundException(String message) {
        super(message);
    }
}
