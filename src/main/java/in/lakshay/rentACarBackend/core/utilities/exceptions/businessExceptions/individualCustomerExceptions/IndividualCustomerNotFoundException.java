package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when we cant find an individual customer in the db
// usually means someone tried to access with wrong ID or the person doesn't exist
public class IndividualCustomerNotFoundException extends BusinessException {

    // just a simple constructor
    public IndividualCustomerNotFoundException(String message) {
        super(message);  // let parent handle the msg
    }

    // hmm should we add more info here? prob not needed
    // maybe add a constructor with customer details later
}
