package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CustomerNotFoundException extends BusinessException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
