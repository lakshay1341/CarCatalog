package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class DeliveredKilometerAlreadyExistsException extends BusinessException {

    public DeliveredKilometerAlreadyExistsException(String message) {
        super(message);
    }
}
