package in.lakshay.rentACarBackend.api.controllers;

// service layer
import in.lakshay.rentACarBackend.business.abstracts.CorporateCustomerService;

// dtos for data transfer
import in.lakshay.rentACarBackend.business.dtos.corporateCustomerDtos.gets.GetCorporateCustomerDto;
import in.lakshay.rentACarBackend.business.dtos.corporateCustomerDtos.lists.CorporateCustomerListDto;

// request objects - so many of these!
import in.lakshay.rentACarBackend.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import in.lakshay.rentACarBackend.business.requests.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import in.lakshay.rentACarBackend.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;

// exceptions - omg so many of these!
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions.CorporateCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions.TaxNumberAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions.CreditCardAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.CustomerNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CustomerAlreadyExistsInRentalCarException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserEmailNotValidException;

// result wrappers
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

// spring stuff
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

// controller for corporate customers (vs individual)
// handles all the REST endpoints for corporate customer operations
@RestController
@RequestMapping("/api/corporateCustomers") // endpoint for corporate customers
public class CorporateCustomersController {
    // business customers, not regular people

    private final CorporateCustomerService corporateCustomerService; // service dependency

    @Autowired // spring DI
    public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
        this.corporateCustomerService = corporateCustomerService; // save reference
    }

    // TODO: add logging here someday



    // get all corporate customers
    @GetMapping("/getAll")  // GET /api/corporateCustomers/getAll
    public DataResult<List<CorporateCustomerListDto>> getAll(){
        // just delegate to service layer
        return this.corporateCustomerService.getAll();  // nothing fancy here
    }

    // add new corporate customer
    @PostMapping("/add")  // POST /api/corporateCustomers/add
    public Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) throws TaxNumberAlreadyExistsException, UserAlreadyExistsException {
        // validation happens via annotations
        return this.corporateCustomerService.add(createCorporateCustomerRequest);  // just pass it along
    }

    // update existing corporate customer
    @PutMapping("/update")  // PUT /api/corporateCustomers/update
    public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws CorporateCustomerNotFoundException, TaxNumberAlreadyExistsException, UserEmailNotValidException {
        // so many exceptions... need to refactor someday
        // srsly this is getting out of hand
        return this.corporateCustomerService.update(updateCorporateCustomerRequest);  // delegate to service
    }

    // delete a corporate customer
    @DeleteMapping("/delete") // maybe use path var instead?
    public Result delete(@RequestBody @Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws CorporateCustomerNotFoundException, CreditCardAlreadyExistsException, CustomerAlreadyExistsInRentalCarException, CustomerNotFoundInInvoiceException {
        // omg this method signature is ridiculous lol
        // like srsly who designed this API?? oh wait it was me
        return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);  // just pass to service
    }

    // get corporate customer by id
    @GetMapping("/getById")  // GET /api/corporateCustomers/getById?corporateCustomerId=123
    public DataResult<GetCorporateCustomerDto> getById(@RequestParam int corporateCustomerId) throws CorporateCustomerNotFoundException {
        // simple lookup
        return this.corporateCustomerService.getById(corporateCustomerId);  // nothing fancy
    }

    // might add search by company name later

    // TODO: add search by company name?

}
