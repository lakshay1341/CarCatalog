package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.PosServiceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class MakePaymentFailedException extends BusinessException {

    public MakePaymentFailedException(String message) {
        super(message);
    }
}
