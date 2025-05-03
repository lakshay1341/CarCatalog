package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to delete a color that's still used by cars
// gotta update or delete the cars first before removing the color
public class ColorExistsInCarException extends BusinessException {

    // basic constructor
    public ColorExistsInCarException(String message) {
        super(message);  // just pass to parent
    }

    // todo: maybe add a constructor that takes colorId param?
    // would make error msgs more consistent
}
