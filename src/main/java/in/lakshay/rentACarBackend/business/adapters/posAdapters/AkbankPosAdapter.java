package in.lakshay.rentACarBackend.business.adapters.posAdapters;

import in.lakshay.rentACarBackend.business.outServices.AkbankPosService;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.PosServiceExceptions.MakePaymentFailedException;
import org.springframework.stereotype.Service;

// adapter for akbank payment gateway
@Service
public class AkbankPosAdapter implements PosService {

    // process payment thru akbank
    @Override
    public boolean payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws MakePaymentFailedException {
        // create service instance - maybe should be injected? whatevs works for now
        AkbankPosService akbankPosService = new AkbankPosService();

        // try to make payment - note param order is weird here
        if(!akbankPosService.makePayment(cardOwner, cardNumber, cardCvv, cardExpirationDate, totalPrice)){
            // payment rejected :(
            throw new MakePaymentFailedException("payment failed, Akbank"); // todo: add more details?
        }
        return true; // all good!
    }
}
