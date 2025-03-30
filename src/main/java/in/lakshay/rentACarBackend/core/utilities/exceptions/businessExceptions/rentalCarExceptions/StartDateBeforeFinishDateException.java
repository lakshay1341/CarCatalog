package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class StartDateBeforeFinishDateException extends BusinessException {

    public StartDateBeforeFinishDateException(String message) {
        super(message);
    }
}
