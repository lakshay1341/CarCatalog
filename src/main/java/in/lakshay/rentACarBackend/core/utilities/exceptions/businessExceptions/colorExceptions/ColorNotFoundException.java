package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions;

// parent exception class
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when color not found in db - usually means bad id
// happens when someone tries to access a color that doesn't exist
// or was deleted
public class ColorNotFoundException extends BusinessException {

    // basic constructor - just takes error message
    public ColorNotFoundException(String message) {
        super(message);  // pass to parent class
    }

    // hmm should we add more info here? prob not needed
    // maybe add a constructor that takes colorId?
    // would make error msgs more consistent
}
