package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to delete a car that's currently rented out
// can't delete cars that are actively being used by customers!
public class CarAlreadyExistsInRentalCarException extends BusinessException {

    // basic constructor - nothing fancy
    public CarAlreadyExistsInRentalCarException(String message) {
        super(message);  // parent handles the msg
    }

    // todo: maybe add a method to get rental details?
    // would be helpful for debugging
}
