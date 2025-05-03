package in.lakshay.rentACarBackend.business.abstracts;

// dto and entity imports
import in.lakshay.rentACarBackend.business.dtos.customerDtos.lists.CustomerListDto;
import in.lakshay.rentACarBackend.business.dtos.customerDtos.gets.GetCustomerDto;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.entities.concretes.Customer;

import java.util.List; // for collections

// main interface for customer operations
// base class for both individual and corporate customers
// implemented by CustomerManager
public interface CustomerService {

    // gets all customers as dto list - both individual and corporate
    DataResult<List<CustomerListDto>> getAll(); // no filtering yet

    // find customer by id, throws exception if not found
    // used by frontend to get customer details
    DataResult<GetCustomerDto> getById(int customerId) throws CustomerNotFoundException;

    // helper method to check if customer exists - used by other services
    // throws if customer not found - cleaner than returning boolean
    void checkIfCustomerIdExists(int customerId) throws CustomerNotFoundException;

    // gets actual customer entity - useful for internal ops
    // doesn't return dto - just the raw entity for service layer use
    Customer getCustomerById(int customerId); // no exception handling here - be careful!

    // todo: maybe add search by name/email?
    // todo: add pagination support?

}

