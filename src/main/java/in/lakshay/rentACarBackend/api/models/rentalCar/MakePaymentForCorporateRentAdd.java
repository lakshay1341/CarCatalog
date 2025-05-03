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

// model for corporate rental payments - newer version
// similar to MakePaymentForCorporateCustomer but with credit card req instead of payment req
@Data // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentForCorporateRentAdd {

    // payment details
    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;  // card details

    // rental info
    @NotNull
    @Valid
    CreateRentalCarRequest createRentalCarRequest;  // dates, car, etc

    // optional extras
    @Valid  // validate if present
    List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList;  // gps etc

    // todo: maybe add invoice details field?

}
