package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when we cant find a corporate customer in the db
// usually means someone tried to access with wrong ID or company name
public class CorporateCustomerNotFoundException extends BusinessException {

    // basic constructor
    public CorporateCustomerNotFoundException(String message) {
        super(message);  // pass to parent
    }

    // might add more specific handling later
    // like showing the customer ID that was requested
}
