package in.lakshay.rentACarBackend.business.abstracts;

import in.lakshay.rentACarBackend.business.dtos.corporateCustomerDtos.gets.GetCorporateCustomerDto;
import in.lakshay.rentACarBackend.business.dtos.corporateCustomerDtos.lists.CorporateCustomerListDto;
import in.lakshay.rentACarBackend.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import in.lakshay.rentACarBackend.business.requests.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import in.lakshay.rentACarBackend.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions.CorporateCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions.TaxNumberAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions.CreditCardAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.CustomerNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CustomerAlreadyExistsInRentalCarException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserEmailNotValidException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.entities.concretes.CorporateCustomer;

import java.util.List;

// service interface for corporate customers (businesses)
// handles all business logic for corporate customers
public interface CorporateCustomerService {

    // get all corporate customers
    DataResult<List<CorporateCustomerListDto>> getAll();

    // CRUD operations
    // add new corp customer - checks for existing tax number and email
    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws TaxNumberAlreadyExistsException, UserAlreadyExistsException;

    // update existing corp customer - lots of validation
    Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws TaxNumberAlreadyExistsException, CorporateCustomerNotFoundException, UserEmailNotValidException;

    // delete corp customer - checks for dependencies first
    // can't delete if has credit cards, invoices or active rentals
    Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws CorporateCustomerNotFoundException, CreditCardAlreadyExistsException, CustomerNotFoundInInvoiceException, CustomerAlreadyExistsInRentalCarException;

    // get corp customer by id - returns dto
    DataResult<GetCorporateCustomerDto> getById(int corporateCustomerId) throws CorporateCustomerNotFoundException;

    // get actual entity - for internal use
    CorporateCustomer getCorporateCustomerById(int corporateCustomerId);

    // validation helper - throws if not found
    void checkIfCorporateCustomerIdExists(int corporateCustomerId) throws CorporateCustomerNotFoundException; // todo: add more validation helpers
}
