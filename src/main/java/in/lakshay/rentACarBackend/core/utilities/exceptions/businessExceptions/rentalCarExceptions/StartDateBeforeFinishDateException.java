package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when finish date is before start date
// cuz time travel isn't a feature we support yet lol
public class StartDateBeforeFinishDateException extends BusinessException {

    // basic constructor - nothing fancy
    public StartDateBeforeFinishDateException(String message) {
        super(message);  // parent handles the msg
    }

    // todo: maybe add the actual dates to make debugging easier?
    // would help identify if it's a UI issue or data problem
}
