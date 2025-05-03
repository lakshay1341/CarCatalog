package in.lakshay.rentACarBackend.business.concretes;


import in.lakshay.rentACarBackend.business.abstracts.*; // all the service interfaces
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages; // msgs for users
import in.lakshay.rentACarBackend.business.dtos.individualCustomerDtos.gets.GetIndividualCustomerDto;
import in.lakshay.rentACarBackend.business.dtos.individualCustomerDtos.lists.IndividualCustomerListDto;
import in.lakshay.rentACarBackend.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import in.lakshay.rentACarBackend.business.requests.individualCustomerRequests.DeleteIndividualCustomerRequest;
import in.lakshay.rentACarBackend.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;

// sooo many exceptions... need to refactor this someday
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions.CreditCardAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.IndividualCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.NationalIdentityAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.CustomerNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.CustomerAlreadyExistsInRentalCarException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserEmailNotValidException;

// utils and results
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;

// data access
import in.lakshay.rentACarBackend.dataAccess.abstracts.IndividualCustomerDao;
import in.lakshay.rentACarBackend.entities.concretes.IndividualCustomer;

// spring stuff
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

// java imports
import java.util.List;
import java.util.stream.Collectors;

@Service // handles individual customer business logic
public class IndividualCustomerManager implements IndividualCustomerService {

    // dependencies - lots of em!
    private final IndividualCustomerDao individualCustomerDao; // data access
    private final UserService userService; // user stuff
    private final RentalCarService rentalCarService; // rental operations
    private final InvoiceService invoiceService; // invoice stuff
    private final CreditCardService creditCardService; // payment methods
    private final ModelMapperService modelMapperService; // dto mapping

    @Autowired // spring DI magic
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService, UserService userService,
                                     RentalCarService rentalCarService, InvoiceService invoiceService, @Lazy CreditCardService creditCardService) {
        // @Lazy to avoid circular deps
        this.individualCustomerDao = individualCustomerDao;
        this.userService = userService;
        this.rentalCarService = rentalCarService;
        this.modelMapperService = modelMapperService;
        this.invoiceService = invoiceService;
        this.creditCardService = creditCardService; // need this for payment stuff
    }


    // get all individual customers
    @Override
    public DataResult<List<IndividualCustomerListDto>> getAll() {
        // grab all from db
        List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAll();

        // map to dtos using streams - java 8 ftw
        List<IndividualCustomerListDto> result = individualCustomers.stream().map(individualCustomer -> this.modelMapperService.forDto()
                .map(individualCustomer,IndividualCustomerListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    // add new individual customer
    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NationalIdentityAlreadyExistsException, UserAlreadyExistsException {
        // validate first
        this.userService.checkIfUserEmailNotExists(createIndividualCustomerRequest.getEmail()); // email unique?
        checkIfNationalIdentityNotExists(createIndividualCustomerRequest.getNationalIdentity()); // natl id unique?

        // convert to entity
        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);

        // save to db
        this.individualCustomerDao.save(individualCustomer);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY); // yay it worked
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws NationalIdentityAlreadyExistsException, IndividualCustomerNotFoundException, UserEmailNotValidException {

        checkIfIndividualCustomerIdExists(updateIndividualCustomerRequest.getUserId());
        this.userService.checkIfUserEmailNotExistsForUpdate(updateIndividualCustomerRequest.getUserId(), updateIndividualCustomerRequest.getEmail());
        checkIfNationalIdentityNotExistsForUpdate(updateIndividualCustomerRequest.getUserId(), updateIndividualCustomerRequest.getNationalIdentity());

        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);

        this.individualCustomerDao.save(individualCustomer);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateIndividualCustomerRequest.getUserId());

    }

    @Override
    public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws CreditCardAlreadyExistsException, IndividualCustomerNotFoundException, CustomerNotFoundInInvoiceException, CustomerAlreadyExistsInRentalCarException {

        checkIfIndividualCustomerIdExists(deleteIndividualCustomerRequest.getUserId());
        this.rentalCarService.checkIfRentalCar_CustomerIdNotExists(deleteIndividualCustomerRequest.getUserId());
        this.creditCardService.checkIfNotExistsByCustomer_CustomerId(deleteIndividualCustomerRequest.getUserId());
        this.invoiceService.checkIfNotExistsByCustomer_CustomerId(deleteIndividualCustomerRequest.getUserId());

        this.individualCustomerDao.deleteById(deleteIndividualCustomerRequest.getUserId());

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteIndividualCustomerRequest.getUserId());
    }

    @Override
    public DataResult<GetIndividualCustomerDto> getById(int individualCustomerId) throws IndividualCustomerNotFoundException {

        checkIfIndividualCustomerIdExists(individualCustomerId);

        IndividualCustomer individualCustomer = this.individualCustomerDao.getById(individualCustomerId);

        GetIndividualCustomerDto result = this.modelMapperService.forDto().map(individualCustomer, GetIndividualCustomerDto.class);

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + individualCustomerId);
    }

    @Override
    public IndividualCustomer getIndividualCustomerById(int individualCustomerId){
        return this.individualCustomerDao.getById(individualCustomerId);
    }

    // check if customer exists - helper method
    @Override
    public void checkIfIndividualCustomerIdExists(int individualCustomerId) throws IndividualCustomerNotFoundException {
        if(!this.individualCustomerDao.existsByIndividualCustomerId(individualCustomerId)){
            // not found - throw exception
            throw new IndividualCustomerNotFoundException(BusinessMessages.IndividualCustomerMessages.INDIVIDUAL_CUSTOMER_ID_NOT_FOUND + individualCustomerId);
        }
        // if we get here, customer exists
    }

    // make sure natl id is unique for new customers
    void checkIfNationalIdentityNotExists(String nationalIdentity) throws NationalIdentityAlreadyExistsException {
        if(this.individualCustomerDao.existsByNationalIdentity(nationalIdentity)){
            // already exists - can't have dupes
            throw new NationalIdentityAlreadyExistsException(BusinessMessages.IndividualCustomerMessages.NATIONAL_IDENTITY_ALREADY_EXISTS + nationalIdentity);
        }
        // all good if we get here
    }

    // similar but for updates - ignore the customer's own id
    void checkIfNationalIdentityNotExistsForUpdate(int individualCustomerId, String nationalIdentity) throws NationalIdentityAlreadyExistsException {
        if(this.individualCustomerDao.existsByNationalIdentityAndIndividualCustomerIdIsNot(nationalIdentity, individualCustomerId)){
            // someone else has this natl id
            throw new NationalIdentityAlreadyExistsException(BusinessMessages.IndividualCustomerMessages.NATIONAL_IDENTITY_ALREADY_EXISTS + nationalIdentity);
        }
        // all good if we get here
    }

}
