package in.lakshay.rentACarBackend.business.requests.creditCardRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation stuff
import jakarta.validation.constraints.*;

// request model for creating new credit cards
// handles payment method creation
@Data // lombok
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest { // for adding new cards

    // card number - must be exactly 16 digits
    @NotNull  // can't be null
    @NotBlank // can't be empty
    @Pattern(regexp = "^[0-9]{16}", message = BusinessMessages.CreditCardMessages.CREDIT_CARD_CARD_NUMBER_NOT_VALID) // validation msg
    private String cardNumber; // no spaces or dashes

    // cardholder name - letters only, 5-50 chars
    // that regex is crazy long lol - supports turkish chars
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[abcçdefgğhıijklmnoöprsştuüvwqyzABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVWQYZ ]{5,50}", message = BusinessMessages.CreditCardMessages.CREDIT_CARD_OWNER_NOT_VALID)
    private String cardOwner; // as appears on card

    // security code - must be exactly 3 digits
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{3}", message = BusinessMessages.CreditCardMessages.CREDIT_CARD_CVV_NOT_VALID) // 3 digits only
    private String cardCvv; // security code on back

    // expiration date - 4-5 chars (MM/YY or MM/YYYY)
    @NotNull
    @NotBlank
    @Size(min = 4, max = 5) // MM/YY or MM/YYYY format
    private String cardExpirationDate; // when card expires

    // customer id - not sent from client, set internally
    @JsonIgnore // don't serialize in responses
    private int customerId; // fk to customer

}
