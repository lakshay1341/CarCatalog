package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to add an additional that's already on the rental
// prevents duplicate entries of the same additional service
public class OrderedAdditionalAlreadyExistsException extends BusinessException {

    // basic constructor
    public OrderedAdditionalAlreadyExistsException(String message) {
        super(message);  // let parent handle it
    }

    // hmm should we add more info here? prob not needed
}
