package in.lakshay.rentACarBackend.core.postServices;

import in.lakshay.rentACarBackend.core.outServices.ZiraatBankPostService;
import in.lakshay.rentACarBackend.core.utilities.exception.BusinessException;
import org.springframework.stereotype.Service;

// akbank payment processor
// weirdly using ziraat's service under the hood?? should fix this
@Service
public class AkbankPost implements PostService{

    // process payment thru akbank
    @Override
    public void payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException {
        // FIXME: this is actually using ZiraatBank's service!! need to implement proper akbank integration
        if(!ZiraatBankPostService.payment(cardNumber, cardOwner, cardCvv, cardExpirationDate, totalPrice)){
            throw new BusinessException("payment failed, akbank"); // let caller know which bank failed
        }

        // todo: add logging here
    }
}
