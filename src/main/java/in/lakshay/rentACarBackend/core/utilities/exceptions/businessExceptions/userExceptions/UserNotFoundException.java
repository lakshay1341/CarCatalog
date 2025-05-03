package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when user not in db or invalid id
// happens a lot during login attempts with wrong credentials
public class UserNotFoundException extends BusinessException {

    // basic constructor
    public UserNotFoundException(String message) {
        super(message);  // parent handles the rest
    }

    // todo: add better error msgs?
    // maybe don't expose too much info for security reasons tho
}
