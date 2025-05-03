package in.lakshay.rentACarBackend.business.dtos.paymentDtos.gets;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// simple dto for payment details
// used when fetching a single payment
@Data // lombok magic
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentDto {

    private int paymentId;  // unique id
    private double totalPrice;  // how much they paid
    private int rentalCarId;  // which rental
    private int invoiceId;  // related invoice

    // todo: maybe add payment date and method?

}
