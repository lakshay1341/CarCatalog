package in.lakshay.rentACarBackend.business.requests.create;

// json stuff
import com.fasterxml.jackson.annotation.JsonIgnore;

// lombok - just need @Data, no ctors needed
import lombok.Data;

// request model for creating payments
// note: this is internal only, not exposed to API
@Data  // lombok ftw
public class CreatePaymentRequest {

    // total amount to charge
    @JsonIgnore  // dont include in json - set by server
    private double totalPrice;  // calculated server-side

    // which rental this payment is for
    @JsonIgnore  // dont include in json - set by server
    private int rentalCarId;  // set from context

    //todo: check if this should be NotNull instead of JsonIgnore
    // also need to add payment method (cash/card/etc)
}
