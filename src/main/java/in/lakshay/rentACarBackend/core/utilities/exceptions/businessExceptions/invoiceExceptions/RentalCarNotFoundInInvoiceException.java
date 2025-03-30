package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class RentalCarNotFoundInInvoiceException extends BusinessException {

    public RentalCarNotFoundInInvoiceException(String message) {
        super(message);
    }
}
