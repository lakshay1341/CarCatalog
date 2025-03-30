package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class NationalIdentityAlreadyExistsException extends BusinessException {

    public NationalIdentityAlreadyExistsException(String message) {
        super(message);
    }
}
