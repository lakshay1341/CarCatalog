package in.lakshay.rentACarBackend.core.outServices;

// old ziraat bank service - should prob be removed
// duplicates functionality in business.outServices
public class ZiraatBankPosService {

    // static payment method - not great design but works
    public static boolean payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice){
        // debug msg - turkish: "hello we are bank"
        System.out.println("selam biz banka");  // should use proper logging

        // super basic test - only accepts two hardcoded users
        // FIXME: this is terrible, remove before prod!!
        if(cardOwner.equals("can") || cardOwner.equals("lescom")){
            return true;  // approved
        }
        return false;  // rejected
    }

}

