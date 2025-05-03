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

// model for creating a new rental with optional extras
@Data // lombok saves so much boilerplate
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarAddModel {

    // the main rental info - required
    @NotNull
    @Valid
    private CreateRentalCarRequest createRentalCarRequest;  // dates, car, customer etc

    // optional extras they might want
    @Valid
    @Nullable  // can be null if no extras needed
    private List<CreateOrderedAdditionalForExtraRequest> orderedAdditionals;  // gps, baby seats, etc

    // todo: maybe add promo code field later?

}
