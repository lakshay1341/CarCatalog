package in.lakshay.rentACarBackend.business.requests.update;

// validation annotations
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request model for updating a color
// pretty simple - just need id and new name
@Data  // lombok is neat
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {

	// which color to update
	@NotNull  // cant be null
	@Min(1)   // must be valid id
	private int colorId;  // pk in db

	// new name for the color
	@NotNull  // required
	@NotBlank  // cant be empty
	@Size(min = 3, max = 30)  // reasonable length plz
	private String colorName;  // like "Blue" or "Red"

	// hmm should we add validation msg here?
}
