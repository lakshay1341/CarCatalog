package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class UserEmailNotValidException extends BusinessException {

    public UserEmailNotValidException(String message) {
        super(message);
    }
}
