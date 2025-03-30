package in.lakshay.rentACarBackend.business.abstracts;

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

public interface InvoiceService {

    DataResult<List<InvoiceListDto>> getAll();

    Result add(CreateInvoiceRequest createInvoiceRequest) throws RentalCarNotFoundException;
    void createAndAddInvoice(int rentalCarId, int paymentId) throws AdditionalNotFoundException, RentalCarNotFoundException;

    DataResult<GetIndividualCustomerInvoiceByInvoiceIdDto> getIndividualCustomerInvoiceByInvoiceId(int invoiceId) throws IndividualCustomerNotFoundException, InvoiceNotFoundException;
    DataResult<GetCorporateCustomerInvoiceByInvoiceIdDto> getCorporateCustomerInvoiceByInvoiceId(int invoiceId) throws CorporateCustomerNotFoundException, InvoiceNotFoundException;

    DataResult<GetIndividualCustomerInvoiceByInvoiceNoDto> getIndividualCustomerInvoiceByInvoiceNo(String invoiceNo) throws IndividualCustomerNotFoundException, InvoiceNotFoundException;
    DataResult<GetCorporateCustomerInvoiceByInvoiceNoDto> getCorporateCustomerInvoiceByInvoiceNo(String invoiceNo) throws CorporateCustomerNotFoundException, InvoiceNotFoundException;

    DataResult<GetInvoiceDto> getInvoiceByPayment_PaymentId(int paymentId) throws PaymentNotFoundInInvoiceException, PaymentNotFoundException;
    DataResult<List<InvoiceListDto>> getAllByRentalCar_RentalCarId(int rentalCarId) throws RentalCarNotFoundException;
    DataResult<List<InvoiceListDto>> getAllByCustomer_CustomerId(int customerId) throws CustomerNotFoundException;
    DataResult<List<InvoiceListDto>> findByInvoiceDateBetween(InvoiceGetDateBetweenRequest invoiceGetDateBetweenRequest);

    void checkIfNotExistsByCustomer_CustomerId(int customerId) throws CustomerNotFoundInInvoiceException;
    void checkIfNotExistsByRentalCar_RentalCarId(int rentalCarId) throws RentalCarNotFoundInInvoiceException;

}
