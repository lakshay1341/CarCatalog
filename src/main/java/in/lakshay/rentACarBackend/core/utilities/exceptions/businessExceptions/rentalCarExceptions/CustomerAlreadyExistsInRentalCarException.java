package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CustomerAlreadyExistsInRentalCarException extends BusinessException {

    public CustomerAlreadyExistsInRentalCarException(String message) {
        super(message);
    }
}
