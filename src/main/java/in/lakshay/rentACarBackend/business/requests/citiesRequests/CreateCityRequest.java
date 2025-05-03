package in.lakshay.rentACarBackend.business.requests.citiesRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// request model for creating a new city
// pretty simple - just need the name
@Data  // lombok is a lifesaver
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {

    // validation to ensure we get a valid name
    @NotNull   // can't be null
    @NotBlank  // can't be empty or just spaces
    @Size(min = 3, max = 30)  // reasonable length
    private String cityName;  // like "Istanbul" or "New York"

    // todo: maybe add country/state?
    // could be useful for international rentals
    // also might want to add a code like airport codes

}
