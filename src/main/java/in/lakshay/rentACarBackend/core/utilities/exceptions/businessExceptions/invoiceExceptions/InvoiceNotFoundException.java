package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class InvoiceNotFoundException extends BusinessException {

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
