package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class StartDateBeforeTodayException extends BusinessException {

    public StartDateBeforeTodayException(String message) {
        super(message);
    }
}
