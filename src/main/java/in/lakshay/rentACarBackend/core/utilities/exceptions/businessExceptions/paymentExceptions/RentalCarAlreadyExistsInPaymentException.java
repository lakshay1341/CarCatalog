package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions;

// parent exception
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to create payment for rental that already has one
// prevents duplicate payments for same rental
public class RentalCarAlreadyExistsInPaymentException extends BusinessException {

    // basic constructor
    public RentalCarAlreadyExistsInPaymentException(String message) {
        super(message);  // let parent handle msg
    }

    // might need to add more info here later
    // like payment id that already exists
}
