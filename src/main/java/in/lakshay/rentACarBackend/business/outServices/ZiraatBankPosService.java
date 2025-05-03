package in.lakshay.rentACarBackend.business.outServices;

// simulates ziraat bank payment gateway
// would connect to real API in prod
public class ZiraatBankPosService {

    // process a payment thru ziraat
    public boolean makePayment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice){

        /*
        if(cardOwner.equals("can") || cardOwner.equals("lescom")){
            return true;
        }
        return false;
         */                 //upper part closed for testing purposes

        // just auto-approve everything for now
        // TODO: implement actual validation logic
        // FIXME: don't leave this in prod!!!
        return true;

    }

}

