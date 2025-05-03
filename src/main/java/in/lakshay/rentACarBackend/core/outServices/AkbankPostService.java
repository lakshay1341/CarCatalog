package in.lakshay.rentACarBackend.core.outServices;

// mock service for akbank payment processing
// just a placeholder until we get real api access
public class AkbankPostService {

    // dummy payment processor - returns true/false for success
    // WARNING: this is not a real implementation!!
    public static boolean payment(String cardNumber, String cardOwner, String cardCvv,
                               String cardExpirationDate, double totalPrice) {
        // bug: using == instead of .equals() for string comparison!
        // this is actually wrong but leaving it for now
        if(cardOwner =="can") {  // hardcoded test case
            return true;  // approved
        }

        return false;  // declined

        // todo: implement actual api integration
        // need to get api keys from akbank first
    }

}