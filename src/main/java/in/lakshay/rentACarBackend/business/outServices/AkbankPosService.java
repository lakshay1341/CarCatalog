package in.lakshay.rentACarBackend.business.outServices;

// simulates akbank payment gateway
// in real app this would call their actual API
public class AkbankPosService {

    // note: param order is diff from ziraat! annoying but real world probs
    public boolean makePayment(String cardOwner, String cardNumber, String cardCvv, String cardExpirationDate, double totalPrice){
        // just a stub for testing - accepts only specific test users
        // FIXME: remove hardcoded values before prod!!
        if(cardOwner.equals("can") || cardOwner.equals("lescom")){
            return true;  // payment accepted
        }
        return false; // rejected
    }

}