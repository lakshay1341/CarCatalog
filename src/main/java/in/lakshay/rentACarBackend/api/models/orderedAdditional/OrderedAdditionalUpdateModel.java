package in.lakshay.rentACarBackend.api.models.orderedAdditional;

import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.UpdateOrderedAdditionalRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalUpdateModel {

    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;

    @NotNull
    @Valid
    UpdateOrderedAdditionalRequest updateOrderedAdditionalRequest;

}
