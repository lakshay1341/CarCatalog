package in.lakshay.rentACarBackend.business.concretes;


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
        this.corporateCustomerDao = corporateCustomerDao;
        this.rentalCarService = rentalCarService;
        this.userService = userService;
        this.modelMapperService = modelMapperService;
        this.invoiceService = invoiceService;
        this.creditCardService = creditCardService;
    }

    @Override
    public DataResult<List<CorporateCustomerListDto>> getAll() {

        List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll();

        List<CorporateCustomerListDto> result = corporateCustomers.stream().map(corporateCustomer -> this.modelMapperService.forDto()
                .map(corporateCustomer, CorporateCustomerListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws TaxNumberAlreadyExistsException, UserAlreadyExistsException {

        this.userService.checkIfUserEmailNotExists(createCorporateCustomerRequest.getEmail());
        checkIfTaxNumberNotExists(createCorporateCustomerRequest.getTaxNumber());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);

        this.corporateCustomerDao.save(corporateCustomer);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws TaxNumberAlreadyExistsException, CorporateCustomerNotFoundException, UserEmailNotValidException {

        checkIfCorporateCustomerIdExists(updateCorporateCustomerRequest.getUserId());
        this.userService.checkIfUserEmailNotExistsForUpdate(updateCorporateCustomerRequest.getUserId(), updateCorporateCustomerRequest.getEmail());
        checkIfTaxNumberNotExistsForUpdate(updateCorporateCustomerRequest.getUserId(), updateCorporateCustomerRequest.getTaxNumber());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);

        this.corporateCustomerDao.save(corporateCustomer);

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateCorporateCustomerRequest.getUserId());
    }

    @Override
    public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws CorporateCustomerNotFoundException, CreditCardAlreadyExistsException, CustomerNotFoundInInvoiceException, CustomerAlreadyExistsInRentalCarException {

        checkIfCorporateCustomerIdExists(deleteCorporateCustomerRequest.getUserId());
        this.rentalCarService.checkIfRentalCar_CustomerIdNotExists(deleteCorporateCustomerRequest.getUserId());
        this.creditCardService.checkIfNotExistsByCustomer_CustomerId(deleteCorporateCustomerRequest.getUserId());
        this.invoiceService.checkIfNotExistsByCustomer_CustomerId(deleteCorporateCustomerRequest.getUserId());

        this.corporateCustomerDao.deleteById(deleteCorporateCustomerRequest.getUserId());

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteCorporateCustomerRequest.getUserId());
    }

    @Override
    public DataResult<GetCorporateCustomerDto> getById(int corporateCustomerId) throws CorporateCustomerNotFoundException {

        checkIfCorporateCustomerIdExists(corporateCustomerId);

        CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(corporateCustomerId);

        GetCorporateCustomerDto result = this.modelMapperService.forDto().map(corporateCustomer, GetCorporateCustomerDto.class);

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + corporateCustomerId);
    }

    @Override
    public CorporateCustomer getCorporateCustomerById(int corporateCustomerId){
        return this.corporateCustomerDao.getById(corporateCustomerId);
    }

    @Override
    public void checkIfCorporateCustomerIdExists(int corporateCustomerId) throws CorporateCustomerNotFoundException {
        if(!this.corporateCustomerDao.existsByCorporateCustomerId(corporateCustomerId)){
            throw new CorporateCustomerNotFoundException(BusinessMessages.CorporateCustomerMessages.CORPORATE_CUSTOMER_ID_NOT_FOUND + corporateCustomerId);
        }
    }

    void checkIfTaxNumberNotExists(String taxNumber) throws TaxNumberAlreadyExistsException {
        if(this.corporateCustomerDao.existsByTaxNumber(taxNumber)){
            throw new TaxNumberAlreadyExistsException(BusinessMessages.CorporateCustomerMessages.TAX_NAME_ALREADY_EXISTS + taxNumber);
        }
    }

    void checkIfTaxNumberNotExistsForUpdate(int corporateCustomerId, String taxNumber) throws TaxNumberAlreadyExistsException {
        if(this.corporateCustomerDao.existsByTaxNumberAndCorporateCustomerIdIsNot(taxNumber, corporateCustomerId)){
            throw new TaxNumberAlreadyExistsException(BusinessMessages.CorporateCustomerMessages.TAX_NAME_ALREADY_EXISTS + taxNumber);
        }
    }

}
