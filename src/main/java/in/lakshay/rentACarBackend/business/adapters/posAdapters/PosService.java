package in.lakshay.rentACarBackend.business.adapters.posAdapters;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.PosServiceExceptions.MakePaymentFailedException;

// interface for payment processors
// we can swap diff implementations based on which bank we wanna use
public interface PosService {

    // process a payment
    // returns true if payment successful, throws exception otherwise
    // todo: maybe add support for partial payments?
    boolean payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws MakePaymentFailedException;

}
