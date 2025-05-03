package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when invoice not found in system
// usually means invalid invoice number or deleted invoice
public class InvoiceNotFoundException extends BusinessException {

    // basic constructor - nothing fancy
    public InvoiceNotFoundException(String message) {
        super(message);  // parent handles the msg
    }

    // todo: maybe add a constructor that takes invoice number?
    // would be helpful for consistent error msgs
}
