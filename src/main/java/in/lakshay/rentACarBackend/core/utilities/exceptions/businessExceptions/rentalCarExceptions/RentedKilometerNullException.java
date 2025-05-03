package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when initial odometer reading is missing
// we need starting km to calculate total distance driven!
public class RentedKilometerNullException extends BusinessException {

    // basic constructor - nothing fancy
    public RentedKilometerNullException(String message) {
        super(message);  // parent handles msg
    }

    // todo: maybe add default value option?
    // but that could lead to billing issues
}
