package in.lakshay.rentACarBackend.api.models.rentalCar;

import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.business.requests.rentalCarRequests.UpdateRentalCarRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

// model for updating individual rentals
// similar to corp version but kept separate for future customization
@Data // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentForIndividualRentUpdate {

    // payment info
    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;  // card details

    // updated rental details
    @NotNull
    @Valid
    UpdateRentalCarRequest updateRentalCarRequest;  // new dates etc

    // todo: maybe add cancellation reason field?

}
