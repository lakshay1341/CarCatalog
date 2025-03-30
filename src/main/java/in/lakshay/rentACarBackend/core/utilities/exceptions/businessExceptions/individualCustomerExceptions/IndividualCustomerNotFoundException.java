package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class IndividualCustomerNotFoundException extends BusinessException {

    public IndividualCustomerNotFoundException(String message) {
        super(message);
    }
}
