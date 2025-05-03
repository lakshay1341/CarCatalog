package in.lakshay.rentACarBackend.business.requests.additionalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// request model for updating additional services
// similar to create but includes id
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalRequest {

    // which one to update
    @NotNull
    @Min(1)  // must be valid id
    private int additionalId;  // pk

    // updated name - same rules as create
    @NotNull
    @NotBlank
    @Size(min = 3, max = 300)  // not too short/long
    private String additionalName;  // new name

    // updated price
    @NotNull
    @Min(1)  // still no free stuff!
    private double additionalDailyPrice;  // new price

    // updated max units
    @NotNull
    @Min(1)  // at least 1
    private short maxUnitsPerRental;  // new limit

    // pretty much same as create but with id field
}
