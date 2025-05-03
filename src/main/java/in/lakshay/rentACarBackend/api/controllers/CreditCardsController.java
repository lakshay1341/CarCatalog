package in.lakshay.rentACarBackend.api.controllers;

import in.lakshay.rentACarBackend.business.abstracts.CreditCardService;
import in.lakshay.rentACarBackend.business.dtos.creditCardDtos.gets.GetCreditCardDto;
import in.lakshay.rentACarBackend.business.dtos.creditCardDtos.lists.CreditCardListDto;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.creditCardExceptions.CreditCardNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// controller for credit card operations
// handles saving/retrieving payment methods
@RestController
@RequestMapping("/api/creditCards") // endpoint for card stuff
public class CreditCardsController {

    private final CreditCardService creditCardService; // service layer

    @Autowired // spring DI
    public CreditCardsController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService; // save reference
    }

    // TODO: maybe add some security here? credit card info is sensitive!


    // get all credit cards - probably not a good idea in production lol
    // should be admin-only or something
    @GetMapping("/getAll")
    public DataResult<List<CreditCardListDto>> getAll(){
        return this.creditCardService.getAll(); // just delegate to service
    }

    // get card by id
    @GetMapping("/getById")
    public DataResult<GetCreditCardDto> getById(@RequestParam int creditCardId) throws CreditCardNotFoundException {
        // simple pass-through to service
        return this.creditCardService.getById(creditCardId); // throws if not found
    }


    // get all cards for a customer - useful for returning users
    // so they dont have to re-enter card info
    @GetMapping("/getAllCreditCardByCustomer_CustomerId") // wow thats a long endpoint name
    public DataResult<List<CreditCardListDto>> getAllCreditCardByCustomer_CustomerId(@RequestParam int customerId) throws CustomerNotFoundException {
        // todo: add pagination? some ppl might have lots of cards i guess
        return this.creditCardService.getAllCreditCardByCustomer_CustomerId(customerId); // delegate to service
    }

    // might need to add delete endpoint later
    // also maybe add card validation?

}
