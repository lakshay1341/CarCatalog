package in.lakshay.rentACarBackend.api.models.rentalCar;


import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.CreateOrderedAdditionalForExtraRequest;
import in.lakshay.rentACarBackend.business.requests.rentalCarRequests.CreateRentalCarRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarAddModel {

    @NotNull
    @Valid
    private CreateRentalCarRequest createRentalCarRequest;

    @Valid
    @Nullable
    private List<CreateOrderedAdditionalForExtraRequest> orderedAdditionals;

}
