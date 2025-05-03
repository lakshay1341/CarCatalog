package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when email format is invalid
// like missing @ or using weird chars
public class UserEmailNotValidException extends BusinessException {

    // simple constructor - just passes msg to parent
    public UserEmailNotValidException(String message) {
        super(message);  // let parent handle it
    }

    // maybe add some examples of valid formats?
    // would help users fix their mistakes
}
