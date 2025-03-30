package in.lakshay.rentACarBackend.business.abstracts;

import in.lakshay.rentACarBackend.business.concretes.CreditCardManager;
import in.lakshay.rentACarBackend.business.dtos.creditCardDtos.gets.GetCreditCardDto;
import in.lakshay.rentACarBackend.business.dtos.creditCardDtos.lists.CreditCardListDto;
import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions.CreditCardAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions.CreditCardNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

import java.util.List;

public interface CreditCardService {

    DataResult<List<CreditCardListDto>> getAll();

    Result add(CreateCreditCardRequest createCreditCardRequest) throws CustomerNotFoundException;

    DataResult<GetCreditCardDto> getById(int creditCardId) throws CreditCardNotFoundException;
    DataResult<List<CreditCardListDto>> getAllCreditCardByCustomer_CustomerId(int customerId) throws CustomerNotFoundException;

    void checkSaveInformationAndSaveCreditCard(CreateCreditCardRequest createCreditCardRequest, CreditCardManager.CardSaveInformation cardSaveInformation) throws CustomerNotFoundException;

    void checkIfNotExistsByCustomer_CustomerId(int customerId) throws CreditCardAlreadyExistsException;

}
