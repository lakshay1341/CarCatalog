package in.lakshay.rentACarBackend.api.controllers;

import in.lakshay.rentACarBackend.business.abstracts.IndividualCustomerService;
import in.lakshay.rentACarBackend.business.dtos.individualCustomerDtos.gets.GetIndividualCustomerDto;
import in.lakshay.rentACarBackend.business.dtos.individualCustomerDtos.lists.IndividualCustomerListDto;
import in.lakshay.rentACarBackend.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import in.lakshay.rentACarBackend.business.requests.individualCustomerRequests.DeleteIndividualCustomerRequest;
import in.lakshay.rentACarBackend.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions.CreditCardAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.IndividualCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.NationalIdentityAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.CustomerNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CustomerAlreadyExistsInRentalCarException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserEmailNotValidException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

// controller for individual customers (vs corporate)
// handles all the REST endpoints for individual customer operations
@RestController
@RequestMapping("/api/individualCustomers") // endpoint for individual customer stuff
public class IndividualCustomersController {

    private final IndividualCustomerService individualCustomerService;  // service layer

    @Autowired // spring DI
    public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService; // save reference
    }

    // TODO: maybe add some logging here later?


    // get all individual customers
    @GetMapping("/getAll")
    public DataResult<List<IndividualCustomerListDto>> getAll(){
        // just delegate to service layer
        return this.individualCustomerService.getAll();  // pretty simple
    }

    // add new individual customer
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NationalIdentityAlreadyExistsException, UserAlreadyExistsException {
        // wow thats a lot of exceptions... maybe refactor someday?
        return this.individualCustomerService.add(createIndividualCustomerRequest); // just pass it along
    }

    // update existing customer
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws NationalIdentityAlreadyExistsException, IndividualCustomerNotFoundException, UserEmailNotValidException {
        // todo: better error handling?
        return this.individualCustomerService.update(updateIndividualCustomerRequest);  // delegate to service
    }

    // delete a customer
    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws CreditCardAlreadyExistsException, IndividualCustomerNotFoundException, CustomerAlreadyExistsInRentalCarException, CustomerNotFoundInInvoiceException {
        // omg so many exceptions... this is getting ridiculous
        // srsly need to refactor this at some point
        return this.individualCustomerService.delete(deleteIndividualCustomerRequest); // delegate to service
    }

    // get customer by id
    @GetMapping("/getById")
    public DataResult<GetIndividualCustomerDto> getById(@RequestParam int individualCustomerId) throws IndividualCustomerNotFoundException {
        // simple pass-through to service
        return this.individualCustomerService.getById(individualCustomerId);
    }

    // might need to add more endpoints later for searching etc

}
