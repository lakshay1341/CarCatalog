package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class RentalCarAlreadyExistsInPaymentException extends BusinessException {

    public RentalCarAlreadyExistsInPaymentException(String message) {
        super(message);
    }
}
