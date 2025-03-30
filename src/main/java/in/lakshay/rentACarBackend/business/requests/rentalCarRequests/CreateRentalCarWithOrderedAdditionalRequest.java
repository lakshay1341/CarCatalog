package in.lakshay.rentACarBackend.business.requests.rentalCarRequests;

import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.CreateOrderedAdditionalForExtraRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalCarWithOrderedAdditionalRequest {

    @NotNull
    @Valid
    private CreateRentalCarRequest createRentalCarRequest;

    /*******FOR ORDERED***************/

    @Valid
    private List<CreateOrderedAdditionalForExtraRequest> orderedAdditionals;

}
