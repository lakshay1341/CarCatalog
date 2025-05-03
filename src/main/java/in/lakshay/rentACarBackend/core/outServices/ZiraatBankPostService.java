package in.lakshay.rentACarBackend.core.outServices;

// mock service for ziraat bank payment processing
// this is just a placeholder for the real api
// todo: replace with actual api integration
public class ZiraatBankPostService {

    // process a payment - returns true if successful
    // this is just a dummy implementation for testing
    public static boolean payment(String cardNumber, String cardOwner, String cardCvv,
                               String cardExpirationDate, double totalPrice) {
        // debug msg
        System.out.println("selam biz banka");  // turkish greeting lol

        // super basic validation - just checks if owner is "can"
        // obvs not how real validation works!
        if(cardOwner.equals("can")) {
            return true;  // payment accepted
        }

        return false;  // payment rejected

        // note: need to add proper validation + api calls
        // when we get the real api credentials
    }

}

