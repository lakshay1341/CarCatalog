package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions;

// parent exception class
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when car not found in db - usually means bad id
// happens when someone tries to access a car that doesn't exist
// or was deleted
public class CarNotFoundException extends BusinessException {

    // constructor takes error message
    public CarNotFoundException(String message) {
        super(message);  // pass to parent class
    }

    // todo: maybe add more specific error handling later?
    // todo: add constructor that takes carId param?
    // would make error msgs more consistent
}
