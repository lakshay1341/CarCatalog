package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when we cant find a car crash record
// usually means someone tried to access with wrong ID or the record was deleted
public class CarCrashNotFoundException extends BusinessException {

    // basic constructor
    public CarCrashNotFoundException(String message) {
        super(message);  // let parent handle the msg
    }

    // might add more specific handling later
}
