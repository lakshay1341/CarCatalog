package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to access an invoice with a customer that doesn't match
// this is a weird edge case - shouldn't happen in normal operation
// but we need to handle it anyway for security reasons
public class CustomerNotFoundInInvoiceException extends BusinessException {

    // basic constructor
    public CustomerNotFoundInInvoiceException(String message) {
        super(message);  // let parent handle it
    }

    // might need to add more specific error handling later
    // but for now this is fine
}
