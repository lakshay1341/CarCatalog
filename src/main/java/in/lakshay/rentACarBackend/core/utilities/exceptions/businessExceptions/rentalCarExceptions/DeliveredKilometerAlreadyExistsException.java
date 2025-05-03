package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to set delivered kilometer when it's already set
// prevents accidental updates to final odometer reading
public class DeliveredKilometerAlreadyExistsException extends BusinessException {

    // simple constructor - nothing fancy
    public DeliveredKilometerAlreadyExistsException(String message) {
        super(message);  // parent handles msg
    }

    // maybe add a method to show current vs attempted value?
    // would help debug weird update attempts
}
