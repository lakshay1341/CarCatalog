package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions;

// parent exception class
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when customer not found in db - usually means bad id
// happens when someone tries to access a customer that doesn't exist
// or was deleted
public class CustomerNotFoundException extends BusinessException {

    // basic constructor - just takes error message
    public CustomerNotFoundException(String message) {
        super(message);  // pass to parent class
    }

    // todo: maybe add constructor that takes customerId param?
    // would make error msgs more consistent
}
