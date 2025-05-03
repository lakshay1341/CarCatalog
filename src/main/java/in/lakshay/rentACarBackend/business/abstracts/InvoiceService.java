package in.lakshay.rentACarBackend.business.abstracts;

// sooo many imports for this one - invoice stuff is complex!
import in.lakshay.rentACarBackend.business.dtos.invoiceDtos.gets.*;
import in.lakshay.rentACarBackend.business.dtos.invoiceDtos.lists.InvoiceListDto;
import in.lakshay.rentACarBackend.business.requests.invoiceRequests.CreateInvoiceRequest;
import in.lakshay.rentACarBackend.business.requests.invoiceRequests.InvoiceGetDateBetweenRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions.CorporateCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.IndividualCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.CustomerNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.InvoiceNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.PaymentNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.RentalCarNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions.PaymentNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.RentalCarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

import java.util.List;

// handles all the billing stuff - receipts for customers
// this is a complex one with lots of methods
// implemented by InvoiceManager
public interface InvoiceService {

    // basic listing - get all invoices
    // prob only used by admins
    DataResult<List<InvoiceListDto>> getAll();

    // create invoices - two ways to do it
    Result add(CreateInvoiceRequest createInvoiceRequest) throws RentalCarNotFoundException;  // manual creation - admin use
    void createAndAddInvoice(int rentalCarId, int paymentId) throws AdditionalNotFoundException, RentalCarNotFoundException;  // auto creation - called after payment

    // get invoice by id - different methods for diff customer types
    // we need separate methods cuz the DTOs are different
    DataResult<GetIndividualCustomerInvoiceByInvoiceIdDto> getIndividualCustomerInvoiceByInvoiceId(int invoiceId) throws IndividualCustomerNotFoundException, InvoiceNotFoundException; // for people
    DataResult<GetCorporateCustomerInvoiceByInvoiceIdDto> getCorporateCustomerInvoiceByInvoiceId(int invoiceId) throws CorporateCustomerNotFoundException, InvoiceNotFoundException; // for companies

    // get by invoice number - again split by customer type
    // invoice numbers are like "INV-12345" - more user friendly than ids
    DataResult<GetIndividualCustomerInvoiceByInvoiceNoDto> getIndividualCustomerInvoiceByInvoiceNo(String invoiceNo) throws IndividualCustomerNotFoundException, InvoiceNotFoundException; // for people
    DataResult<GetCorporateCustomerInvoiceByInvoiceNoDto> getCorporateCustomerInvoiceByInvoiceNo(String invoiceNo) throws CorporateCustomerNotFoundException, InvoiceNotFoundException; // for companies

    // find by related entities - useful for lookups
    DataResult<GetInvoiceDto> getInvoiceByPayment_PaymentId(int paymentId) throws PaymentNotFoundInInvoiceException, PaymentNotFoundException;  // by payment - find receipt for a payment
    DataResult<List<InvoiceListDto>> getAllByRentalCar_RentalCarId(int rentalCarId) throws RentalCarNotFoundException;  // by rental - all invoices for a rental
    DataResult<List<InvoiceListDto>> getAllByCustomer_CustomerId(int customerId) throws CustomerNotFoundException;  // by customer - all invoices for a customer

    // date range search - for reports etc
    // useful for accounting and financial reports
    DataResult<List<InvoiceListDto>> findByInvoiceDateBetween(InvoiceGetDateBetweenRequest invoiceGetDateBetweenRequest);

    // validation helpers - used by other services
    void checkIfNotExistsByCustomer_CustomerId(int customerId) throws CustomerNotFoundInInvoiceException;  // check if customer has invoices
    void checkIfNotExistsByRentalCar_RentalCarId(int rentalCarId) throws RentalCarNotFoundInInvoiceException;  // check if rental has invoice

    // todo: maybe add some reporting methods? would be nice for accounting
    // todo: add export to PDF?
    // todo: add email invoice functionality?
}
