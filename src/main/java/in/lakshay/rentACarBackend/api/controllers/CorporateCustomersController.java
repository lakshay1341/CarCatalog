package in.lakshay.rentACarBackend.api.controllers;

import in.lakshay.rentACarBackend.business.abstracts.CorporateCustomerService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/corporateCustomers")
public class CorporateCustomersController {

    private final CorporateCustomerService corporateCustomerService;

    @Autowired
    public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
        this.corporateCustomerService = corporateCustomerService;
    }


    @GetMapping("/getAll")
    public DataResult<List<CorporateCustomerListDto>> getAll(){
        return this.corporateCustomerService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) throws TaxNumberAlreadyExistsException, UserAlreadyExistsException {
        return this.corporateCustomerService.add(createCorporateCustomerRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws CorporateCustomerNotFoundException, TaxNumberAlreadyExistsException, UserEmailNotValidException {
        return this.corporateCustomerService.update(updateCorporateCustomerRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws CorporateCustomerNotFoundException, CreditCardAlreadyExistsException, CustomerAlreadyExistsInRentalCarException, CustomerNotFoundInInvoiceException {
        return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
    }

    @GetMapping("/getById")
    public DataResult<GetCorporateCustomerDto> getById(@RequestParam int corporateCustomerId) throws CorporateCustomerNotFoundException {
        return this.corporateCustomerService.getById(corporateCustomerId);
    }

}
