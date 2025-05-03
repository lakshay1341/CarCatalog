package in.lakshay.rentACarBackend.business.dtos.lists.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing payments
// used in financial reports and customer history
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentListDto {

    private int paymentId;     // pk
    private double totalPrice;  // amount paid
    private int rentalCarId;   // which rental this is for
    private int invoiceId;     // linked invoice

    // todo: add payment method and date
    // would make the reports more useful

}
