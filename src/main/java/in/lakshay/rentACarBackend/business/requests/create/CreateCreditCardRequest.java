package in.lakshay.rentACarBackend.business.requests.create;

// json stuff
import com.fasterxml.jackson.annotation.JsonIgnore;

// lombok makes life easier
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation - import all cuz lazy
import jakarta.validation.constraints.*;

// request model for saving credit cards
// used when customers want to save payment info
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {

    // card number - must be 16 digits
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Pattern(regexp = "^[0-9]{16}", message = "must be 16 digits") //todo: fix error msg
    private String cardNumber;  // no spaces or dashes

    // cardholder name
    @NotNull
    @NotBlank
    private String cardOwner;  // as printed on card

    // security code on back
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{3}", message = "must be 3 digits") //todo: fix error msg
    private String cardCvv;  // 3 digit code on back

    // expiration date - MM/YY or MM/YYYY format
    @NotNull
    @NotBlank
    @Size(min = 4, max = 5)  // MM/YY or MM/YYYY
    private String cardExpirationDate;  // like "12/25" or "12/2025"

    // which customer owns this card
    // hidden from json because it's set from auth context
    @NotNull
    @Min(1)  // valid customer id
    @JsonIgnore  // dont include in json
    private int customerId;  // fk to customers table

    // todo: should prob add card type (visa/mc/amex)
    // also need to encrypt this data before storing!!

}
