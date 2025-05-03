package in.lakshay.rentACarBackend.business.dtos.lists;

// dto for listing credit cards
// SECURITY ISSUE: we're returning full card details in lists!!
// TODO: fix this before prod - mask numbers, remove CVV!!
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // lombok magic
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardListDto {

    private int creditCardId;  // pk
    private String cardNumber;  // should mask this!! (****1234)
    private String cardOwner;   // name on card
    private String cardCvv;     // NEVER return this in prod!!
    private String cardExpirationDate;  // MM/YY format
    private int customerId;     // fk to customer

    // need to add validation before this goes live
}
