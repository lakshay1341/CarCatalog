package in.lakshay.rentACarBackend.business.requests.create;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

// collections
import java.util.List;

// request model for creating rental with extras
// combines rental request with add-ons in one request
@Data  // lombok is neat
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalCarWithOrderedAdditionalRequest {

    // the main rental request
    @NotNull  // must have a rental
    @Valid    // must be valid
    private CreateRentalCarRequest createRentalCarRequest;  // base rental

    // extras/add-ons for the rental
    // stuff like gps, child seat, etc
    @Valid  // must be valid if present (can be null tho)
    private List<CreateOrderedAdditionalForExtraRequest> orderedAdditionals;  // optional extras

}
//todo: delete this class - not sure if we need it anymore