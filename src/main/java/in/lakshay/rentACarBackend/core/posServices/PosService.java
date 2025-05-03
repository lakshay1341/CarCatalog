package in.lakshay.rentACarBackend.core.posServices;

import in.lakshay.rentACarBackend.core.utilities.exception.BusinessException;

// older version of pos service interface
// TODO: consolidate with the one in adapters pkg
// not sure why we have 2 of these :/
public interface PosService {

    // process a payment - throws exception if fails
    void payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException;

}
