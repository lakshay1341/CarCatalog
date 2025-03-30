package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carCrashExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CrashDateAfterTodayException extends BusinessException {

    public CrashDateAfterTodayException(String message) {
        super(message);
    }
}
