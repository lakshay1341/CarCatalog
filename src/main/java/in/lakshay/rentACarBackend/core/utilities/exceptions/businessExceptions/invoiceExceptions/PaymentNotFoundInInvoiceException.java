package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when payment not found in invoice
// happens when someone tries to access an invoice with wrong payment info
// or when payment record was deleted but invoice still exists
public class PaymentNotFoundInInvoiceException extends BusinessException {

    // basic constructor - nothing fancy
    public PaymentNotFoundInInvoiceException(String message) {
        super(message);  // let parent handle the msg
    }

    // todo: maybe add more specific error handling?
    // like showing payment details or invoice number
}
