package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions;

// base exception
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when payment not found in db
// usually means invalid payment id or deleted payment
public class PaymentNotFoundException extends BusinessException {

    // just pass msg to parent
    public PaymentNotFoundException(String message) {
        super(message); // parent handles the rest
    }

    // todo: maybe add constructor that takes payment id?
}
