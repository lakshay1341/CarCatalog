package in.lakshay.rentACarBackend.core.postServices;

import in.lakshay.rentACarBackend.core.outServices.ZiraatBankPostService;
import in.lakshay.rentACarBackend.core.utilities.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class AkbankPost implements PostService{

    @Override
    public void payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException {

        if(!ZiraatBankPostService.payment(cardNumber, cardOwner, cardCvv, cardExpirationDate, totalPrice)){
            throw new BusinessException("payment failed, akbank");
        }
    }
}
