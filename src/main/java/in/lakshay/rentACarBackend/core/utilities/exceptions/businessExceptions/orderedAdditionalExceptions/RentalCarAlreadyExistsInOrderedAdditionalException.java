package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to delete a rental that has ordered additionals
// can't delete rentals with active additional services
public class RentalCarAlreadyExistsInOrderedAdditionalException extends BusinessException {

    // basic constructor - nothing fancy
    public RentalCarAlreadyExistsInOrderedAdditionalException(String message) {
        super(message);  // parent handles the msg
    }

    // todo: maybe add constructor that shows which additionals are ordered?
    // would make debugging easier
}
