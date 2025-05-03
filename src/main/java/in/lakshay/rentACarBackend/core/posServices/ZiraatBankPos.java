package in.lakshay.rentACarBackend.core.posServices;

import in.lakshay.rentACarBackend.core.outServices.ZiraatBankPosService;
import in.lakshay.rentACarBackend.core.utilities.exception.BusinessException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// older ziraat bank implementation
// this is the default one (@Primary)
@Service
@Primary
public class ZiraatBankPos implements PosService {

    @Override
    public void payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException {
        // debug logs - should use proper logging
        System.out.println("ziraate gelindi");  // turkish: "ziraat reached"

        // try payment
        if(!ZiraatBankPosService.payment(cardNumber, cardOwner, cardCvv, cardExpirationDate, totalPrice)){
            throw new BusinessException("payment failed, ziraat");
        }

        // more debug
        System.out.println("ziraat ödeme alındı");  // turkish: "ziraat payment received"
    }
}
