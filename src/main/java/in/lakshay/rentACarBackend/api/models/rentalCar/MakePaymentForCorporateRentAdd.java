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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentForCorporateRentAdd {

    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;

    @NotNull
    @Valid
    CreateRentalCarRequest createRentalCarRequest;

    @Valid
    List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList;

}
