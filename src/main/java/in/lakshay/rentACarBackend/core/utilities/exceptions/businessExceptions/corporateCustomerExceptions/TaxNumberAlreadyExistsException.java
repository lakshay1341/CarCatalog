package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class TaxNumberAlreadyExistsException extends BusinessException {

    public TaxNumberAlreadyExistsException(String message) {
        super(message);
    }
}
