package in.lakshay.rentACarBackend.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// request model for creating a new additional/extra item
// like GPS, child seat, etc that can be added to rentals
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalRequest {

    // name of the extra item
    @NotNull   // can't be null
    @NotBlank  // can't be empty
    @Size(min = 3, max = 300)  // reasonable length
    private String additionalName;  // like "GPS" or "Child Seat"

    // daily cost to add this item
    @NotNull
    @Min(1)  // must cost something - no freebies!
    private double additionalDailyPrice;  // per day cost

    // how many can be added to a single rental
    @NotNull
    @Min(1)  // at least 1 obvs
    private short maxUnitsPerRental;  // like max 3 child seats

    // todo: maybe add description field?
    // also could add an image url for the UI

}
