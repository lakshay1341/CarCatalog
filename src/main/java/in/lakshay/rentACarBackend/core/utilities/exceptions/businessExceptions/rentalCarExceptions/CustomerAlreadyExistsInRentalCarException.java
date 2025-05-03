package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to delete a customer who has active rentals
// can't delete customers with ongoing rentals - would mess up the system!
public class CustomerAlreadyExistsInRentalCarException extends BusinessException {

    // basic constructor - nothing special here
    public CustomerAlreadyExistsInRentalCarException(String message) {
        super(message);  // parent handles the msg
    }

    // todo: maybe add a way to get the active rental ids?
    // would help admins resolve the issue
}
