package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when rental car not found in invoice
// happens when someone tries to access invoice with wrong rental info
// or when rental record was deleted but invoice still exists
public class RentalCarNotFoundInInvoiceException extends BusinessException {

    // simple constructor
    public RentalCarNotFoundInInvoiceException(String message) {
        super(message);  // pass to parent
    }

    // might need to add better error handling later
    // like showing which rental car was requested
}
