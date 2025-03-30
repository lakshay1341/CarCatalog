package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class RentedKilometerNullException extends BusinessException {

    public RentedKilometerNullException(String message) {
        super(message);
    }
}
