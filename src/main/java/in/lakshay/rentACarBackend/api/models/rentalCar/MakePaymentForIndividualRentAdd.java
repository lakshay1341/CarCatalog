package in.lakshay.rentACarBackend.api.models.rentalCar;

import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.CreateOrderedAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.rentalCarRequests.CreateRentalCarRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

// model for individual rental payments - newer version
// similar to MakePaymentForIndividualCustomer but with credit card req instead of payment req
@Data // lombok is a lifesaver
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentForIndividualRentAdd {

    // payment info
    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;  // card details

    // rental details
    @NotNull
    @Valid
    CreateRentalCarRequest createRentalCarRequest;  // dates, car, etc

    // optional extras
    @Valid  // validate if present
    List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList;  // gps etc

    // note: we should probably consolidate some of these similar models

}
