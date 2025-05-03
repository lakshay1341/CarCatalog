package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to add a credit card that already exists in the system
// card numbers must be unique - can't have duplicates for security reasons
public class CreditCardAlreadyExistsException extends BusinessException {

    // basic constructor
    public CreditCardAlreadyExistsException(String message) {
        super(message);  // pass to parent
    }

    // might add a constructor that takes masked card number later
    // would make error msgs more consistent and secure
}
