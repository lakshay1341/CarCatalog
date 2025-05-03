package in.lakshay.rentACarBackend.business.abstracts;

// dtos
import in.lakshay.rentACarBackend.business.dtos.individualCustomerDtos.lists.IndividualCustomerListDto;
import in.lakshay.rentACarBackend.business.dtos.individualCustomerDtos.gets.GetIndividualCustomerDto;

// requests
import in.lakshay.rentACarBackend.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import in.lakshay.rentACarBackend.business.requests.individualCustomerRequests.DeleteIndividualCustomerRequest;
import in.lakshay.rentACarBackend.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;

// omg so many exceptions... this is getting ridiculous
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions.CreditCardAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.IndividualCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.NationalIdentityAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.CustomerNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CustomerAlreadyExistsInRentalCarException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserEmailNotValidException;

// result wrappers
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

// entities
import in.lakshay.rentACarBackend.entities.concretes.IndividualCustomer;

import java.util.List;

// service interface for individual customers (vs corporate)
// implemented by IndividualCustomerManager
public interface IndividualCustomerService {

    // get all individual customers
    DataResult<List<IndividualCustomerListDto>> getAll();

    // CRUD operations
    // add new customer - throws if natl id already exists or user email exists
    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NationalIdentityAlreadyExistsException, UserAlreadyExistsException;

    // update existing customer - lots of validation
    Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws NationalIdentityAlreadyExistsException, IndividualCustomerNotFoundException, UserEmailNotValidException;

    // delete customer - wow thats a lot of exceptions
    // todo: maybe refactor this to be simpler?
    Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws CreditCardAlreadyExistsException, IndividualCustomerNotFoundException, CustomerNotFoundInInvoiceException, CustomerAlreadyExistsInRentalCarException;

    // get customer by id - returns dto
    DataResult<GetIndividualCustomerDto> getById(int individualCustomerId) throws IndividualCustomerNotFoundException;

    // get actual entity - internal use only
    // no validation here so be careful!
    IndividualCustomer getIndividualCustomerById(int individualCustomerId);

    // helper to check if customer exists
    // throws if not found
    void checkIfIndividualCustomerIdExists(int individualCustomerId) throws IndividualCustomerNotFoundException;

    // might need to add search by national id later
}

