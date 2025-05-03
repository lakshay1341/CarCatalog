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

// service interface for credit card operations
// handles payment methods for customers
// gotta be careful with this stuff - PCI compliance and all that
public interface CreditCardService {

    // get all cards - prob should be admin only
    DataResult<List<CreditCardListDto>> getAll();

    // add new card - needs valid customer
    Result add(CreateCreditCardRequest createCreditCardRequest) throws CustomerNotFoundException;

    // get card by id
    DataResult<GetCreditCardDto> getById(int creditCardId) throws CreditCardNotFoundException;

    // get all cards for a customer - useful for returning users
    DataResult<List<CreditCardListDto>> getAllCreditCardByCustomer_CustomerId(int customerId) throws CustomerNotFoundException;

    // only save card if user wants to (checkbox in UI)
    // this is kinda a weird method name tbh
    void checkSaveInformationAndSaveCreditCard(CreateCreditCardRequest createCreditCardRequest, CreditCardManager.CardSaveInformation cardSaveInformation) throws CustomerNotFoundException;

    // check if customer already has cards - for validation
    // throws if cards exist
    void checkIfNotExistsByCustomer_CustomerId(int customerId) throws CreditCardAlreadyExistsException;

    // TODO: add card validation?
    // TODO: add card deletion?

}
