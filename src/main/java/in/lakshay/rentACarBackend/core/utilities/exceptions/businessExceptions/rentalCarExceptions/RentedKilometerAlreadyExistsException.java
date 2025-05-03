package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to update initial odometer reading after rental starts
// we lock this value to prevent fraud - can't change starting km after rental begins!
public class RentedKilometerAlreadyExistsException extends BusinessException {

    // basic constructor - nothing special
    public RentedKilometerAlreadyExistsException(String message) {
        super(message);  // let parent handle the details
    }

    // todo: maybe add a way to override in special cases?
    // would need manager approval or something
}
