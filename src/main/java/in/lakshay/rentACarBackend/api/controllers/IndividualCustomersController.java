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

@RestController
@RequestMapping("/api/individualCustomers")
public class IndividualCustomersController {

    private final IndividualCustomerService individualCustomerService;

    @Autowired
    public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
        this.individualCustomerService = individualCustomerService;
    }


    @GetMapping("/getAll")
    public DataResult<List<IndividualCustomerListDto>> getAll(){
        return this.individualCustomerService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NationalIdentityAlreadyExistsException, UserAlreadyExistsException {
        return this.individualCustomerService.add(createIndividualCustomerRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws NationalIdentityAlreadyExistsException, IndividualCustomerNotFoundException, UserEmailNotValidException {
        return this.individualCustomerService.update(updateIndividualCustomerRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws CreditCardAlreadyExistsException, IndividualCustomerNotFoundException, CustomerAlreadyExistsInRentalCarException, CustomerNotFoundInInvoiceException {
        return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
    }

    @GetMapping("/getById")
    public DataResult<GetIndividualCustomerDto> getById(@RequestParam int individualCustomerId) throws IndividualCustomerNotFoundException {
        return this.individualCustomerService.getById(individualCustomerId);
    }

}
