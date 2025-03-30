package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CarAlreadyRentedEnteredDateException extends BusinessException {

    public CarAlreadyRentedEnteredDateException(String message) {
        super(message);
    }
}
