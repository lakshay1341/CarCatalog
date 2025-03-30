package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CarAlreadyExistsInRentalCarException extends BusinessException {

    public CarAlreadyExistsInRentalCarException(String message) {
        super(message);
    }
}
