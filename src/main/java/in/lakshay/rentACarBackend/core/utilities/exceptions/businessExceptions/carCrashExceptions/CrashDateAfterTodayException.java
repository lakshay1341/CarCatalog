package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when someone tries to set a crash date in the future
// cuz u know... we cant predict accidents lol
public class CrashDateAfterTodayException extends BusinessException {

    // basic constructor
    public CrashDateAfterTodayException(String message) {
        super(message);  // pass to parent
    }

    // might add a constructor that takes the date later
}
