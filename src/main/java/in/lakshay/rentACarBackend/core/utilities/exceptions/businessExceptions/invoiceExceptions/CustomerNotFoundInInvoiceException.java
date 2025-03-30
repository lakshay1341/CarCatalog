package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CustomerNotFoundInInvoiceException extends BusinessException {

    public CustomerNotFoundInInvoiceException(String message) {
        super(message);
    }
}
