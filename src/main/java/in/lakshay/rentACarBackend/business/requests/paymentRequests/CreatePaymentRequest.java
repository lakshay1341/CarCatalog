package in.lakshay.rentACarBackend.business.requests.paymentRequests;

// json stuff
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

// request model for creating payments
// note: this is internal only, not exposed to API
@Data  // lombok ftw
public class CreatePaymentRequest {

    // these are ignored in json because they're set by the server
    // not by the client request

    @JsonIgnore  // don't serialize
    private double totalPrice;  // calculated server-side

    @JsonIgnore  // don't serialize
    private int rentalCarId;  // set from context

    // todo: should we add payment method here? cash/card/etc

}
