package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to rent a car on dates it's already booked
// or when trying to put car in maintenance when it's rented
public class CarAlreadyRentedEnteredDateException extends BusinessException {

    // simple constructor - just passes msg to parent
    public CarAlreadyRentedEnteredDateException(String message) {
        super(message);  // let parent handle it
    }

    // hmm maybe we should add the conflicting dates to the error?
    // would make it easier for users to pick different dates
}
