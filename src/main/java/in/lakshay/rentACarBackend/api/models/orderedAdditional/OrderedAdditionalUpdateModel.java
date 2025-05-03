package in.lakshay.rentACarBackend.api.models.orderedAdditional;

import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.UpdateOrderedAdditionalRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

// model for updating extras on a rental
@Data // thx lombok
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalUpdateModel {

    // payment info for the change
    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;  // might charge more/less

    // the actual update details
    @NotNull
    @Valid
    UpdateOrderedAdditionalRequest updateOrderedAdditionalRequest;  // new quantity etc

    // note: we get the rental id from the update request

}
