package in.lakshay.rentACarBackend.business.dtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for credit card info
// note: we should probably mask some of this data in responses
// and make sure we're PCI compliant!
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCreditCardDto {

    private int creditCardId;  // pk in db
    private String cardNumber;  // should be masked except last 4

    private String cardOwner;  // name on card
    private String cardCvv;    // security code - should we even return this?

    private String cardExpirationDate;  // MM/YY format
    private int customerId;    // which customer owns this card

    // todo: add card type? (visa/mc/amex)
    // todo: implement proper masking for PCI compliance

}
