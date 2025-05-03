package in.lakshay.rentACarBackend.business.concretes;

// service interface
import in.lakshay.rentACarBackend.business.abstracts.CustomerService;

// constants and dtos
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.dtos.customerDtos.lists.CustomerListDto;
import in.lakshay.rentACarBackend.business.dtos.customerDtos.gets.GetCustomerDto;

// exceptions and utilities
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;

// data access and entities
import in.lakshay.rentACarBackend.dataAccess.abstracts.CustomerDao;
import in.lakshay.rentACarBackend.entities.concretes.Customer;

// spring stuff
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// java imports
import java.util.List;
import java.util.stream.Collectors;

@Service // handles customer business logic
public class CustomerManager implements CustomerService {
    // manages customer operations - both individual and corporate customers

    private final CustomerDao customerDao; // data access
    private final ModelMapperService modelMapperService; // for mapping

    @Autowired
    public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
        this.customerDao = customerDao;
        this.modelMapperService = modelMapperService; // for entity <-> dto conversion
    }

    // get all customers
    @Override
    public DataResult<List<CustomerListDto>> getAll() {
        // fetch all customers from db
        List<Customer> customers = this.customerDao.findAll();

        // map to dtos using streams - nice pattern but kinda verbose
        List<CustomerListDto> result = customers.stream()
                .map(customer -> this.modelMapperService.forDto().map(customer, CustomerListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    // get customer by id
    @Override
    public DataResult<GetCustomerDto> getById(int customerId) throws CustomerNotFoundException {
        // check if customer exists
        checkIfCustomerIdExists(customerId);

        // get from db
        Customer customer = this.customerDao.getById(customerId);

        // convert to dto
        GetCustomerDto result = this.modelMapperService.forDto().map(customer, GetCustomerDto.class);

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + customerId);
    }

    // helper method to check if customer exists
    @Override
    public void checkIfCustomerIdExists(int customerId) throws CustomerNotFoundException {
        if(!this.customerDao.existsByCustomerId(customerId)){
            // oops, customer not found!
            throw new CustomerNotFoundException(BusinessMessages.CustomerMessages.CUSTOMER_ID_NOT_FOUND + customerId);
        }
    }

    // get customer entity directly (not dto)
    @Override
    public Customer getCustomerById(int customerId){
        // note: doesn't check if exists - be careful when using this!
        return this.customerDao.getById(customerId);
    }

    // todo: add methods for customer stats, like total rentals, etc.

}
