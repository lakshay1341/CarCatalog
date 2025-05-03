package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.colorExceptions;

// exception for when someone tries to create a color that already exists
// thrown during validation in ColorManager.add()
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class ColorAlreadyExistsException extends BusinessException {

    // just passes msg to parent class
    // nothing special here
    public ColorAlreadyExistsException(String message) {
        super(message);  // parent handles the rest
    }

    // todo: maybe add a constructor that takes the color name?
    // would make it easier to create consistent error msgs
}
