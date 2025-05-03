package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when we cant find a credit card in the db
// usually means someone tried to access with wrong ID or card number
public class CreditCardNotFoundException extends BusinessException {

    // basic constructor
    public CreditCardNotFoundException(String message) {
        super(message);  // pass to parent
    }

    // might add more specific handling later
    // like showing a masked version of the card that was requested
}
