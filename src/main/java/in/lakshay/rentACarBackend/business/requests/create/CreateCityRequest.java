package in.lakshay.rentACarBackend.business.requests.create;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation annotations
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// simple request for adding new cities to the system
// pretty basic - just need the name
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateCityRequest {

    // city name with validation
    @NotNull   // cant be null
    @NotBlank  // cant be empty
    @Size(min = 3, max = 30)  // reasonable length plz
    private String cityName;  // like "Istanbul" or "New York"

    // todo: maybe add country or region?
    // could also add coordinates for map stuff later

}
