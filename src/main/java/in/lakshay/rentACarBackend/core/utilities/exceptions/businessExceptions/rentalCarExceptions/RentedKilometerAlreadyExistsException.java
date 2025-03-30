package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class RentedKilometerAlreadyExistsException extends BusinessException {

    public RentedKilometerAlreadyExistsException(String message) {
        super(message);
    }
}
