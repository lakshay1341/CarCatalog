package in.lakshay.rentACarBackend.api.models.orderedAdditional;

import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.CreateOrderedAdditionalRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalAddModel {

    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;

    @NotNull
    @Valid
    List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList;

    @NotNull
    @Min(1)
    private int rentalCarId;

}
