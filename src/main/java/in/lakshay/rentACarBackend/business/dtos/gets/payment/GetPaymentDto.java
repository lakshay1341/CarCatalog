package in.lakshay.rentACarBackend.business.dtos.gets.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// payment info dto - pretty basic for now
// just tracks the payment record details
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentDto {

    private int paymentId;      // pk in db
    private double totalPrice;  // how much was paid

    // links to related records
    private int rentalCarId;    // which rental this paid for
    private int invoiceId;      // which invoice this paid

    // todo: add payment method? date? status?
    // maybe add transaction id from payment processor?

}
