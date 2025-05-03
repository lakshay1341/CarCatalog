package in.lakshay.rentACarBackend.core.outServices;

// old akbank service - prob should be removed
// duplicates functionality in business.outServices
public class AkbankPosService {

    // static payment method - not ideal design but whatevs
    public static boolean payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice){
        // super basic test - only accepts one hardcoded user
        // FIXME: this is terrible, remove before prod!!
        if(cardOwner =="can"){
            return true;  // approved
        }
        return false;  // rejected
    }

}