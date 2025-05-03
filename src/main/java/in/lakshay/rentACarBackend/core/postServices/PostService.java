package in.lakshay.rentACarBackend.core.postServices;

import in.lakshay.rentACarBackend.core.utilities.exception.BusinessException;

// interface for payment processing services
// similar to PostOutService but actually used
public interface PostService {

    // process a payment with card details
    // throws exception if payment fails
    void payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException;

    // maybe add more methods later like refund, etc

}
