package in.lakshay.rentACarBackend.api.models.rentalCar;

import in.lakshay.rentACarBackend.business.requests.create.CreateOrderedAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.create.CreatePaymentRequest;
import in.lakshay.rentACarBackend.business.requests.create.CreateRentalCarRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

// model for corporate customer payments
// almost identical to individual but kept separate for future corp-specific features
@Data // lombok saves typing
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentForCorporateCustomer {

    // payment details - corp card usually
    @NotNull
    @Valid
    CreatePaymentRequest createPaymentRequest;  // card info etc

    // rental details
    @NotNull
    @Valid
    CreateRentalCarRequest createRentalCarRequest;  // dates, car, etc

    // optional extras - can be null
    @Valid  // still validate if present
    List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList;  // gps, etc

    // todo: add corp discount field?

}
