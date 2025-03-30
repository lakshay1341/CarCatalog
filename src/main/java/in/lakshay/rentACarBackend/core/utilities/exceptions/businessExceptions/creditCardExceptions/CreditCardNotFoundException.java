package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CreditCardNotFoundException extends BusinessException {

    public CreditCardNotFoundException(String message) {
        super(message);
    }
}
