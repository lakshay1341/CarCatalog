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

// request model for updating cities
// pretty simple - just need id and new name
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {

    // which city to update
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int cityId;  // pk in db

    // new name for the city
    @NotNull   // required
    @NotBlank  // cant be empty
    @Size(min = 3, max = 30)  // reasonable length
    private String cityName;  // like "Istanbul" or "New York"

    // todo: maybe add country or region?
    // could also add coordinates for map stuff

}
