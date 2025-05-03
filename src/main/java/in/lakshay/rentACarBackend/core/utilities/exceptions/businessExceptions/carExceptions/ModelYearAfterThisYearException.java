package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when someone tries to add a car with model year in the future
// we're not a time machine rental company lol
public class ModelYearAfterThisYearException extends BusinessException {

    // basic constructor
    public ModelYearAfterThisYearException(String message) {
        super(message);  // pass to parent
    }

    // might add more specific handling later
    // like showing both the entered year and current year
}
