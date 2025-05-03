package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when quantity of additional service is invalid
// like trying to order 5 GPS units when max is 1
public class AdditionalQuantityNotValidException extends BusinessException {

    // simple constructor
    public AdditionalQuantityNotValidException(String message) {
        super(message);  // parent handles msg
    }

    // todo: add constructor that takes requested and max quantity?
    // would make error msgs more helpful
}
