package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to do something with a car that's already in a crash record
// prevents deleting cars that have crash records etc
public class CarExistsInCarCrashException extends BusinessException {

    // simple constructor
    public CarExistsInCarCrashException(String message) {
        super(message);  // nothing special here, just pass to parent
    }

    // maybe add more constructors later if needed
}
