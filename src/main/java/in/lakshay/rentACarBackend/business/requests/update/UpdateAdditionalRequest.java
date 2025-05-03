package in.lakshay.rentACarBackend.business.requests.update;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// request model for updating additional/extra items
// like GPS, child seat, etc that can be added to rentals
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalRequest {

    // which extra to update
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int additionalId;  // pk in db

    // updated name
    @NotNull   // required
    @NotBlank  // cant be empty
    @Size(min = 3, max = 300)  // reasonable length
    private String additionalName;  // like "GPS" or "Child Seat"

    // updated price
    @NotNull
    @Min(1)  // still no freebies!
    private double additionalDailyPrice;  // per day cost

    // updated max units
    @NotNull
    @Min(1)  // at least 1 obvs
    private short maxUnitsPerRental;  // like max 3 child seats

    // todo: maybe add description field?
    // also could add an image url for the UI

}
