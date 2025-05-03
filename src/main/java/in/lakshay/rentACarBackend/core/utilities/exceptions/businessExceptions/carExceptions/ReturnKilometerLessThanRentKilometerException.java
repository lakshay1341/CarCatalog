package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when return km is less than rent km
// cars can't drive backwards... unless they're really weird cars
// or someone tampered with the odometer which is illegal btw
public class ReturnKilometerLessThanRentKilometerException extends BusinessException {

    // basic constructor
    public ReturnKilometerLessThanRentKilometerException(String message) {
        super(message);  // pass to parent
    }

    // might add constructor that takes both values later
    // would make error msgs more consistent
}
