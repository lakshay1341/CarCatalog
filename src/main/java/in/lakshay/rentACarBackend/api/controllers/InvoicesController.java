package in.lakshay.rentACarBackend.api.controllers;

// all the imports we need for invoice stuff
import in.lakshay.rentACarBackend.business.abstracts.InvoiceService;
import in.lakshay.rentACarBackend.business.dtos.invoiceDtos.gets.*;
import in.lakshay.rentACarBackend.business.dtos.invoiceDtos.lists.InvoiceListDto;
import in.lakshay.rentACarBackend.business.requests.invoiceRequests.InvoiceGetDateBetweenRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions.CorporateCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.IndividualCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.InvoiceNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.PaymentNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions.PaymentNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.RentalCarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/invoices") // endpoint for invoice operations
public class InvoicesController {

    private final InvoiceService invoiceService;  // service layer dependency

    @Autowired  // spring di magic
    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService; // inject the service
    }


    // get all invoices - admin only
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResult<List<InvoiceListDto>> getAll(){
        return this.invoiceService.getAll();  // just pass thru to service
    }

    // get invoice for individual customer by id
    @GetMapping("/getIndividualCustomerInvoiceByInvoiceId")
    public DataResult<GetIndividualCustomerInvoiceByInvoiceIdDto> getIndividualCustomerInvoiceByInvoiceId(@RequestParam int invoiceId) throws InvoiceNotFoundException, IndividualCustomerNotFoundException {
        return this.invoiceService.getIndividualCustomerInvoiceByInvoiceId(invoiceId); // delegate to service
    }

    // same but for corporate customers
    @GetMapping("/getCorporateCustomerInvoiceByInvoiceId")
    public DataResult<GetCorporateCustomerInvoiceByInvoiceIdDto> getCorporateCustomerInvoiceByInvoiceId(@RequestParam int invoiceId) throws CorporateCustomerNotFoundException, InvoiceNotFoundException {
        return this.invoiceService.getCorporateCustomerInvoiceByInvoiceId(invoiceId); // these methods are getting repetitive...
    }

    // find by invoice number instead of id
    @GetMapping("/getIndividualCustomerInvoiceByInvoiceNo")
    public DataResult<GetIndividualCustomerInvoiceByInvoiceNoDto> getIndividualCustomerInvoiceByInvoiceNo(@RequestParam String invoiceNo) throws InvoiceNotFoundException, IndividualCustomerNotFoundException {
        return this.invoiceService.getIndividualCustomerInvoiceByInvoiceNo(invoiceNo); // should add validation here maybe?
    }

    // corporate version of the same thing
    @GetMapping("/getCorporateCustomerInvoiceByInvoiceNo")
    public DataResult<GetCorporateCustomerInvoiceByInvoiceNoDto> getCorporateCustomerInvoiceByInvoiceNo(@RequestParam String invoiceNo) throws CorporateCustomerNotFoundException, InvoiceNotFoundException {
        return this.invoiceService.getCorporateCustomerInvoiceByInvoiceNo(invoiceNo); // TODO: refactor these to be more DRY
    }

    // find invoice by payment id - useful for receipts
    @GetMapping("/getInvoiceByPayment_PaymentId")
    public DataResult<GetInvoiceDto> getInvoiceByPayment_PaymentId(@RequestParam int paymentId) throws PaymentNotFoundInInvoiceException, PaymentNotFoundException {
        return this.invoiceService.getInvoiceByPayment_PaymentId(paymentId); // 1:1 relationship
    }

    // get all invoices for a specific rental
    @GetMapping("/getAllByRentalCar_RentalCarId")
    public DataResult<List<InvoiceListDto>> getAllByRentalCar_RentalCarId(@RequestParam int rentalCarId) throws RentalCarNotFoundException {
        return this.invoiceService.getAllByRentalCar_RentalCarId(rentalCarId);  // usually just 1 but could be multiple
    }

    // get customer invoice history
    @GetMapping("/getAllByCustomer_CustomerId")
    public DataResult<List<InvoiceListDto>> getAllByCustomer_CustomerId(@RequestParam int customerId) throws CustomerNotFoundException {
        return this.invoiceService.getAllByCustomer_CustomerId(customerId); // customer history
    }

    // date range search - useful for reports
    @GetMapping("/getDateBetween")
    public DataResult<List<InvoiceListDto>> findByInvoiceDateBetween(@RequestBody @Valid InvoiceGetDateBetweenRequest invoiceGetDateBetweenRequest){
        return this.invoiceService.findByInvoiceDateBetween(invoiceGetDateBetweenRequest); // for accounting
    }

    // might add more endpoints later for analytics and stuff
    // TODO: add endpoint for monthly revenue reports

}

