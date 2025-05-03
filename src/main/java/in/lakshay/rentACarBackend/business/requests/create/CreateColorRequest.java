package in.lakshay.rentACarBackend.business.requests.create;

// validation annotations
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// lombok makes coding less tedious
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request model for creating a new car color
// pretty simple - just need the name
@Data  // lombok is a lifesaver
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {

	// validation to ensure we get a valid color name
	@NotNull   // cant be null
	@NotBlank  // cant be empty or just spaces
	@Size(min = 3, max = 30)  // no crazy long color names plz
	private String colorName;  // like "Red" or "Midnight Blue"

	// hmm maybe should add hex code for UI?
	// like #FF0000 for red etc
}
