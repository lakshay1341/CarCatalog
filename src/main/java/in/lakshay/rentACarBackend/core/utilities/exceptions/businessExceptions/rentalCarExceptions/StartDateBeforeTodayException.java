package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when someone tries to book a rental starting in the past
// we don't have time machines... yet! :)
public class StartDateBeforeTodayException extends BusinessException {

    // simple constructor - just passes msg to parent
    public StartDateBeforeTodayException(String message) {
        super(message);  // let parent handle it
    }

    // hmm should we add a grace period? like allow bookings for earlier today?
    // might be useful for walk-in customers
}
