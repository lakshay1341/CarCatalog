package in.lakshay.rentACarBackend.business.abstracts;

import in.lakshay.rentACarBackend.business.dtos.customerDtos.lists.CustomerListDto;
import in.lakshay.rentACarBackend.business.dtos.customerDtos.gets.GetCustomerDto;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.entities.concretes.Customer;

import java.util.List;

public interface CustomerService {

    DataResult<List<CustomerListDto>> getAll();
    DataResult<GetCustomerDto> getById(int customerId) throws CustomerNotFoundException;

    void checkIfCustomerIdExists(int customerId) throws CustomerNotFoundException;

    Customer getCustomerById(int customerId);

}

