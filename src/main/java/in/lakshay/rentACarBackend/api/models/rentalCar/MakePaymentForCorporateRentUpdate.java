package in.lakshay.rentACarBackend.api.models.rentalCar;

import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.business.requests.rentalCarRequests.UpdateRentalCarRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

// model for updating corporate rentals
// simpler than add model since we don't need extras list (handled separately)
@Data // lombok rocks
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentForCorporateRentUpdate {

    // payment info
    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;  // card details

    // updated rental details
    @NotNull
    @Valid
    UpdateRentalCarRequest updateRentalCarRequest;  // new dates etc

    // note: extras handled thru separate endpoint

}
