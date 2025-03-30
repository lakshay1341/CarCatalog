package in.lakshay.rentACarBackend.core.postServices;

import in.lakshay.rentACarBackend.core.utilities.exception.BusinessException;

public interface PostService {

    void payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException;

}
