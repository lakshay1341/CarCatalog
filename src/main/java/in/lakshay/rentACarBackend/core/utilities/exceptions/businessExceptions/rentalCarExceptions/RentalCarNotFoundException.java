package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class RentalCarNotFoundException extends BusinessException {

    public RentalCarNotFoundException(String message) {
        super(message);
    }
}
