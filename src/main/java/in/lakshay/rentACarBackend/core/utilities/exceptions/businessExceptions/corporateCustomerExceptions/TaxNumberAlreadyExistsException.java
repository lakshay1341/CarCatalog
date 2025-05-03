package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to add a corporate customer with a tax number that already exists
// tax numbers must be unique in our system - can't have duplicates
public class TaxNumberAlreadyExistsException extends BusinessException {

    // basic constructor
    public TaxNumberAlreadyExistsException(String message) {
        super(message);  // pass to parent
    }

    // might add a constructor that takes the tax number later
    // would make error msgs more consistent
}
