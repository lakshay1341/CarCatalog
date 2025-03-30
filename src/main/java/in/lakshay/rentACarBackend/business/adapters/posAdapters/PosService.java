package in.lakshay.rentACarBackend.business.adapters.posAdapters;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.PosServiceExceptions.MakePaymentFailedException;

public interface PosService {

    boolean payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws MakePaymentFailedException;

}
