package in.lakshay.rentACarBackend.core.posServices;

import in.lakshay.rentACarBackend.core.utilities.exception.BusinessException;

public interface PosService {

    void payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException;

}
