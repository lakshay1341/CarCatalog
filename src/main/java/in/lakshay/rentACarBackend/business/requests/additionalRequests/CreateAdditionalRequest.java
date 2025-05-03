package in.lakshay.rentACarBackend.business.requests.additionalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// request model for creating new additional services
// like gps, baby seats, etc
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalRequest {

    // name - required, 3-300 chars
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Size(min = 3, max = 300)  // reasonable length
    private String additionalName;  // what is it called

    // price - must be positive
    @NotNull
    @Min(1)  // no free stuff!
    private double additionalDailyPrice;  // cost per day

    // max units - must be positive
    @NotNull
    @Min(1)  // at least 1
    private short maxUnitsPerRental;  // how many can be added to one rental

    // todo: maybe add description field?

}
