package in.lakshay.rentACarBackend.business.adapters.posAdapters;

import in.lakshay.rentACarBackend.business.outServices.ZiraatBankPosService;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.PosServiceExceptions.MakePaymentFailedException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// this is our default payment processor
@Service
@Primary  // make this the default impl
public class ZiraatBankPosAdapter implements PosService {

    // process payment via ziraat bank
    @Override
    public boolean payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws MakePaymentFailedException {
        // create service - should prob be injected but this works
        ZiraatBankPosService ziraatBankPosService = new ZiraatBankPosService();

        // attempt payment
        if(!ziraatBankPosService.makePayment(cardNumber, cardOwner, cardCvv, cardExpirationDate, totalPrice)){
            // failed :/
            throw new MakePaymentFailedException("payment failed, Ziraat");
        }
        return true;  // success!
    }
}
