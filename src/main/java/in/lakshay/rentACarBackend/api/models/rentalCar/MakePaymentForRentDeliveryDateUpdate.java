package in.lakshay.rentACarBackend.api.models.rentalCar;

import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.business.requests.rentalCarRequests.UpdateDeliveryDateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

// model for updating just the delivery date of a rental
// used when customer wants to return car earlier/later
@Data // lombok <3
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentForRentDeliveryDateUpdate {

    // payment info - might need to charge more/less
    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;  // card details

    // just the delivery date update
    @NotNull
    @Valid
    UpdateDeliveryDateRequest updateDeliveryDateRequest;  // new return date

    // note: this is a specialized update that only changes return date
    // todo: maybe add reason field for audit purposes?
}
