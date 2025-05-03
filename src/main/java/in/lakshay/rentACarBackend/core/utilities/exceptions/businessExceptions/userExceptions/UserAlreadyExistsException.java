package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to create a user with email that already exists
// prevents duplicate accounts - security thing ya know
public class UserAlreadyExistsException extends BusinessException {

    // basic constructor - nothing fancy
    public UserAlreadyExistsException(String message) {
        super(message);  // parent handles the msg
    }

    // todo: maybe add a suggestion for alternative email?
    // or add a "did u mean to login instead?" hint
}
