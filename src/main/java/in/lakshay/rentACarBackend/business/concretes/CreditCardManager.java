package in.lakshay.rentACarBackend.business.concretes;

import in.lakshay.rentACarBackend.business.abstracts.CreditCardService;
import in.lakshay.rentACarBackend.business.abstracts.CustomerService;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.dtos.creditCardDtos.gets.GetCreditCardDto;
import in.lakshay.rentACarBackend.business.dtos.creditCardDtos.lists.CreditCardListDto;
import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions.CreditCardAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions.CreditCardNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.CreditCardDao;
import in.lakshay.rentACarBackend.entities.concretes.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardManager implements CreditCardService {

    private final CreditCardDao creditCardDao;
    private final CustomerService customerService;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService, CustomerService customerService) {
        this.creditCardDao = creditCardDao;
        this.customerService = customerService;
        this.modelMapperService = modelMapperService;
    }

    public enum CardSaveInformation {
        SAVE, DONT_SAVE;
    }

    @Override
    public DataResult<List<CreditCardListDto>> getAll() {

        List<CreditCard> creditCardList = this.creditCardDao.findAll();

        List<CreditCardListDto> result = creditCardList.stream().map(creditCard -> this.modelMapperService.forDto().map(creditCard, CreditCardListDto.class))
                .collect(Collectors.toList());
        setCustomerIdForAllCreditCard(creditCardList, result);

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public Result add(CreateCreditCardRequest createCreditCardRequest) throws CustomerNotFoundException {

        this.customerService.checkIfCustomerIdExists(createCreditCardRequest.getCustomerId());

        CreditCard creditCard = this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
        creditCard.setCustomer(this.customerService.getCustomerById(createCreditCardRequest.getCustomerId()));

        if(checkIfNotExistsByCardNumber(creditCard.getCardNumber())){
            this.creditCardDao.save(creditCard);

        }
        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    @Override
    public DataResult<GetCreditCardDto> getById(int creditCardId) throws CreditCardNotFoundException {

        checkIfExistsById(creditCardId);

        CreditCard creditCard = this.creditCardDao.getById(creditCardId);

        GetCreditCardDto result = this.modelMapperService.forDto().map(creditCard, GetCreditCardDto.class);
        result.setCustomerId(creditCard.getCustomer().getCustomerId());

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + creditCardId);
    }

    @Override
    public DataResult<List<CreditCardListDto>> getAllCreditCardByCustomer_CustomerId(int customerId) throws CustomerNotFoundException {

        this.customerService.checkIfCustomerIdExists(customerId);

        List<CreditCard> creditCardList = this.creditCardDao.getAllByCustomer_CustomerId(customerId);

        List<CreditCardListDto> result = creditCardList.stream().map(creditCard -> this.modelMapperService.forDto().map(creditCard, CreditCardListDto.class))
                .collect(Collectors.toList());
        setCustomerIdForAllCreditCard(creditCardList, result);

        return new SuccessDataResult<>(result, BusinessMessages.CreditCardMessages.CREDIT_CARD_LISTED_BY_CUSTOMER_ID + customerId);
    }

    private void setCustomerIdForAllCreditCard(List<CreditCard> creditCardList, List<CreditCardListDto> result) {

        for(int i=0; i< creditCardList.size();i++){
            result.get(i).setCustomerId(creditCardList.get(i).getCustomer().getCustomerId());
        }
    }

    @Override
    public void checkSaveInformationAndSaveCreditCard(CreateCreditCardRequest createCreditCardRequest, CreditCardManager.CardSaveInformation cardSaveInformation) throws CustomerNotFoundException {
        if(cardSaveInformation.equals(CreditCardManager.CardSaveInformation.SAVE)){
            add(createCreditCardRequest);
        }
    }

    @Override
    public void checkIfNotExistsByCustomer_CustomerId(int customerId) throws CreditCardAlreadyExistsException {
        if(this.creditCardDao.existsByCustomer_CustomerId(customerId)){
            throw new CreditCardAlreadyExistsException(BusinessMessages.CreditCardMessages.CREDIT_CARD_ALREADY_EXISTS + customerId);
        }
    }

    private boolean checkIfNotExistsByCardNumber(String cardNumber) {
        return !this.creditCardDao.existsByCardNumber(cardNumber);
    }

    private void checkIfExistsById(int creditCardId) throws CreditCardNotFoundException {
        if(!this.creditCardDao.existsByCreditCardId(creditCardId)){
            throw new CreditCardNotFoundException(BusinessMessages.CreditCardMessages.CREDIT_CARD_ID_NOT_FOUND + creditCardId);
        }
    }

}
