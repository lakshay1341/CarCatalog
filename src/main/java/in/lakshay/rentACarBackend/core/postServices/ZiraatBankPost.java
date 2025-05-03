package in.lakshay.rentACarBackend.core.postServices;

import in.lakshay.rentACarBackend.core.outServices.ZiraatBankPostService;
import in.lakshay.rentACarBackend.core.utilities.exception.BusinessException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// ziraat bank payment processor
// this is our default payment processor (@Primary)
@Service
@Primary  // make this the default impl
public class ZiraatBankPost implements PostService {

    // process payment via ziraat bank
    @Override
    public void payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException {
        // debug logs - should use proper logging someday
        System.out.println("ziraate gelindi");  // turkish: "ziraat reached"

         // try payment
         if(!ZiraatBankPostService.payment(cardNumber, cardOwner, cardCvv, cardExpirationDate, totalPrice)){
             throw new BusinessException("payment failed, ziraat");
         }

        // more debug
        System.out.println("ziraat ödeme alındı");  // turkish: "ziraat payment received"
    }
}
