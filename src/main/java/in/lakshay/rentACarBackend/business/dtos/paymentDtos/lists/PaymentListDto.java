package in.lakshay.rentACarBackend.business.dtos.paymentDtos.lists;

// lombok annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing payments
// used in getAll and search results
@Data // saves so much boilerplate!
@AllArgsConstructor // full constructor
@NoArgsConstructor  // empty constructor
public class PaymentListDto {

    // basic payment info
    private int paymentId;      // pk
    private double totalPrice;  // amount
    private int rentalCarId;    // which rental
    private int invoiceId;      // invoice ref

    // might need to add customer info here for admin panel

}
