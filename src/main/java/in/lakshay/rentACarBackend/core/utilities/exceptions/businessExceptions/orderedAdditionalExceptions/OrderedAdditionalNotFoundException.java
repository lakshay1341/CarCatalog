package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when we can't find the ordered additional service
// happens when someone tries to look up an additional that doesn't exist
// or was removed from the order
public class OrderedAdditionalNotFoundException extends BusinessException {

    // simple constructor - nothing special
    public OrderedAdditionalNotFoundException(String message) {
        super(message);  // parent handles the details
    }

    // might need to add more specific handling later
    // like showing which rental the additional was supposed to be for
}
