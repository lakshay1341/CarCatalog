package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to delete an additional service that's already in use
// can't delete things that customers have already ordered!
public class AdditionalAlreadyExistsInOrderedAdditionalException extends BusinessException {

    // basic constructor
    public AdditionalAlreadyExistsInOrderedAdditionalException(String message) {
        super(message);  // pass to parent
    }

    // maybe add more specific error handling later
    // like showing which rentals are using this additional
}
