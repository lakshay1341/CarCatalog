package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class PaymentNotFoundException extends BusinessException {

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
