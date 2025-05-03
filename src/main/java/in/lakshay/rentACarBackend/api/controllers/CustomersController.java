package in.lakshay.rentACarBackend.api.controllers;

// service and dtos
import in.lakshay.rentACarBackend.business.abstracts.CustomerService;
import in.lakshay.rentACarBackend.business.dtos.customerDtos.lists.CustomerListDto;
import in.lakshay.rentACarBackend.business.dtos.customerDtos.gets.GetCustomerDto;

// exceptions and results
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;  // wrapper for responses

// spring mvc stuff
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// java imports
import java.util.List;

@RestController
@RequestMapping("/api/customers") // endpoint for customer operations
public class CustomersController {
    // handles HTTP requests for customer operations
    // this is the base customer controller - individual and corporate extend Customer

    private final CustomerService customerService; // service layer dependency

    @Autowired  // dependency injection magic
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService; // inject the service
    }

    // TODO: maybe add some metrics tracking here?

    // get all customers - simple pass-through to service
    @GetMapping("/getAll")  // GET /api/customers/getAll
    public DataResult<List<CustomerListDto>> getAll(){
        return this.customerService.getAll(); // just delegate to service
        // should probably add pagination at some point
    }

    // get customer by id - missing slash in mapping! should be "/getById"
    @GetMapping("getById") // oops forgot the slash here - too lazy to fix it now
    public DataResult<GetCustomerDto> getById(@RequestParam int customerId) throws CustomerNotFoundException {
        // todo: add caching here maybe?
        return this.customerService.getById(customerId);  // simple lookup
    }

    // todo: add endpoints for customer stats, search by name, etc.
    // maybe add a dashboard endpoint with summary stats?
}
