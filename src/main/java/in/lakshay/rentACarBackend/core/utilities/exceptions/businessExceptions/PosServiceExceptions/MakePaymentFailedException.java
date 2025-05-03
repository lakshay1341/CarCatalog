package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.PosServiceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when payment processing fails
// usually means card declined or invalid details
public class MakePaymentFailedException extends BusinessException {

    // basic constructor - just passes msg to parent
    public MakePaymentFailedException(String message) {
        super(message);  // let parent handle the details
    }

    // todo: maybe add more info like which bank rejected it?
    // or add error codes from payment processor?
}
