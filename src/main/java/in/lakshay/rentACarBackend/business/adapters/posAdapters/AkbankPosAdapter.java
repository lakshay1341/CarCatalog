package in.lakshay.rentACarBackend.business.adapters.posAdapters;

import in.lakshay.rentACarBackend.business.outServices.AkbankPosService;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.PosServiceExceptions.MakePaymentFailedException;
import org.springframework.stereotype.Service;

@Service
public class AkbankPosAdapter implements PosService {

    @Override
    public boolean payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws MakePaymentFailedException {

        AkbankPosService akbankPosService = new AkbankPosService();

        if(!akbankPosService.makePayment(cardOwner, cardNumber, cardCvv, cardExpirationDate, totalPrice)){
            throw new MakePaymentFailedException("payment failed, Akbank");
        }
        return true;
    }
}
