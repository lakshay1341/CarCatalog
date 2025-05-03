package in.lakshay.rentACarBackend.core.posServices;

import in.lakshay.rentACarBackend.core.outServices.ZiraatBankPosService;
import in.lakshay.rentACarBackend.core.utilities.exception.BusinessException;
import org.springframework.stereotype.Service;

// older akbank implementation
// weird that it uses ziraat service internally??
// TODO: figure out if this is still used
@Service
public class AkbankPos implements PosService {

    @Override
    public void payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException {
        // this seems wrong - using ziraat service in akbank impl??
        // FIXME: this is probably a bug
        if(!ZiraatBankPosService.payment(cardNumber, cardOwner, cardCvv, cardExpirationDate, totalPrice)){
            throw new BusinessException("payment failed, akbank");
        }
    }
}
