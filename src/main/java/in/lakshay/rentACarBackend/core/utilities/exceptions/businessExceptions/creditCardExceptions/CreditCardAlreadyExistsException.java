package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CreditCardAlreadyExistsException extends BusinessException {

    public CreditCardAlreadyExistsException(String message) {
        super(message);
    }
}
