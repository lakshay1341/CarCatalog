package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class PaymentNotFoundInInvoiceException extends BusinessException {

    public PaymentNotFoundInInvoiceException(String message) {
        super(message);
    }
}
