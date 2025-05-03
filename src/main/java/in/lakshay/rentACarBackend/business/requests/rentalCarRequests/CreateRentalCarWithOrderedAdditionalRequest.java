package in.lakshay.rentACarBackend.business.requests.rentalCarRequests;

import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.CreateOrderedAdditionalForExtraRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

// request model for creating a rental with extras in one go
// combines the basic rental with optional add-ons
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalCarWithOrderedAdditionalRequest {

    // the main rental request - required
    @NotNull  // must have this
    @Valid    // must be valid
    private CreateRentalCarRequest createRentalCarRequest;  // base rental info

    // extras/add-ons for the rental - optional
    // stuff like gps, child seats, etc
    // weird comment below - prob from copy/paste
    /*******FOR ORDERED***************/

    @Valid  // must be valid if present (can be null tho)
    private List<CreateOrderedAdditionalForExtraRequest> orderedAdditionals;  // optional extras

    // todo: maybe add a way to specify special requests?
    // or a notes field for the whole rental?

}
