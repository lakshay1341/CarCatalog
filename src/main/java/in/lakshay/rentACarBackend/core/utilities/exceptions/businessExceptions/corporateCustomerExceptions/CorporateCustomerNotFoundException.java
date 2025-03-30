package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CorporateCustomerNotFoundException extends BusinessException {

    public CorporateCustomerNotFoundException(String message) {
        super(message);
    }
}
