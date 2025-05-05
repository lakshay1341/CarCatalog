package in.lakshay.rentACarBackend.business.concretes;

// main manager for corporate customers
import in.lakshay.rentACarBackend.business.abstracts.*;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
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
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.CorporateCustomerDao;
import in.lakshay.rentACarBackend.entities.concretes.CorporateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

    private final CorporateCustomerDao corporateCustomerDao;
    private final RentalCarService rentalCarService;
    private final UserService userService;
    private final InvoiceService invoiceService;
    private final CreditCardService creditCardService;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService,
                                    RentalCarService rentalCarService, UserService userService, InvoiceService invoiceService, @Lazy CreditCardService creditCardService) {
        // init all the stuff we need
        this.corporateCustomerDao = corporateCustomerDao;
        this.rentalCarService = rentalCarService;
        this.userService = userService;
        this.modelMapperService = modelMapperService;
        this.invoiceService = invoiceService;
        this.creditCardService = creditCardService;  // need this for card validation
    }

    @Override
    public DataResult<List<CorporateCustomerListDto>> getAll() {
        // grab all corporate customers from db
        List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll();

        List<CorporateCustomerListDto> result = corporateCustomers.stream().map(corporateCustomer -> this.modelMapperService.forDto()
                .map(corporateCustomer, CorporateCustomerListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws TaxNumberAlreadyExistsException, UserAlreadyExistsException {

        // make sure email and tax num are unique
        this.userService.checkIfUserEmailNotExists(createCorporateCustomerRequest.getEmail());
        checkIfTaxNumberNotExists(createCorporateCustomerRequest.getTaxNumber());

        // map the request to entity and save it
        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);

        // encode password before saving
        corporateCustomer.setPassword(this.userService.encodePassword(corporateCustomer.getPassword()));

        this.corporateCustomerDao.save(corporateCustomer);  // todo: maybe add some validation here?

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws TaxNumberAlreadyExistsException, CorporateCustomerNotFoundException, UserEmailNotValidException {

        // gotta check if customer exists first
        checkIfCorporateCustomerIdExists(updateCorporateCustomerRequest.getUserId());
        this.userService.checkIfUserEmailNotExistsForUpdate(updateCorporateCustomerRequest.getUserId(), updateCorporateCustomerRequest.getEmail());
        checkIfTaxNumberNotExistsForUpdate(updateCorporateCustomerRequest.getUserId(), updateCorporateCustomerRequest.getTaxNumber());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);

        // encode password before saving
        corporateCustomer.setPassword(this.userService.encodePassword(corporateCustomer.getPassword()));

        // save it to db
        this.corporateCustomerDao.save(corporateCustomer);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateCorporateCustomerRequest.getUserId());
    }

    @Override
    public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws CorporateCustomerNotFoundException, CreditCardAlreadyExistsException, CustomerNotFoundInInvoiceException, CustomerAlreadyExistsInRentalCarException {

        // lots of checks b4 we can delete
        checkIfCorporateCustomerIdExists(deleteCorporateCustomerRequest.getUserId());
        this.rentalCarService.checkIfRentalCar_CustomerIdNotExists(deleteCorporateCustomerRequest.getUserId());
        this.creditCardService.checkIfNotExistsByCustomer_CustomerId(deleteCorporateCustomerRequest.getUserId());
        this.invoiceService.checkIfNotExistsByCustomer_CustomerId(deleteCorporateCustomerRequest.getUserId());

        // finally can delete if all checks pass
        this.corporateCustomerDao.deleteById(deleteCorporateCustomerRequest.getUserId());

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteCorporateCustomerRequest.getUserId());
    }

    @Override
    public DataResult<GetCorporateCustomerDto> getById(int corporateCustomerId) throws CorporateCustomerNotFoundException {

        checkIfCorporateCustomerIdExists(corporateCustomerId);

        CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(corporateCustomerId);

        // map to dto for response
        GetCorporateCustomerDto result = this.modelMapperService.forDto().map(corporateCustomer, GetCorporateCustomerDto.class);

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + corporateCustomerId);
    }

    @Override
    public CorporateCustomer getCorporateCustomerById(int corporateCustomerId){
        // just a simple getter - no dto mapping needed
        return this.corporateCustomerDao.getById(corporateCustomerId);
    }

    @Override
    public void checkIfCorporateCustomerIdExists(int corporateCustomerId) throws CorporateCustomerNotFoundException {
        // check if customer exists in db
        if(!this.corporateCustomerDao.existsByCorporateCustomerId(corporateCustomerId)){
            throw new CorporateCustomerNotFoundException(BusinessMessages.CorporateCustomerMessages.CORPORATE_CUSTOMER_ID_NOT_FOUND + corporateCustomerId); // cust not found!
        }
    }

    void checkIfTaxNumberNotExists(String taxNumber) throws TaxNumberAlreadyExistsException {
        // make sure tax num is unique
        if(this.corporateCustomerDao.existsByTaxNumber(taxNumber)){
            throw new TaxNumberAlreadyExistsException(BusinessMessages.CorporateCustomerMessages.TAX_NAME_ALREADY_EXISTS + taxNumber); // dupe tax num!
        }
    }

    void checkIfTaxNumberNotExistsForUpdate(int corporateCustomerId, String taxNumber) throws TaxNumberAlreadyExistsException {
        // similar to above but for updates - need to exclude current customer
        if(this.corporateCustomerDao.existsByTaxNumberAndCorporateCustomerIdIsNot(taxNumber, corporateCustomerId)){
            throw new TaxNumberAlreadyExistsException(BusinessMessages.CorporateCustomerMessages.TAX_NAME_ALREADY_EXISTS + taxNumber);
        }
    }

    // might need more validation methods later

}
