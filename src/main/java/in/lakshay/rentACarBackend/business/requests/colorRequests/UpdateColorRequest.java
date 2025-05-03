package in.lakshay.rentACarBackend.business.requests.colorRequests;

// validation stuff
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// lombok ftw
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request for updating colors
// used in PUT /api/colors/update endpoint
@Data  // thx lombok
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {

	// which color to update - must be valid id
	@NotNull  // cant be null
	@Min(1)   // must be positive
	private int colorId;  // pk in db

	// new name for the color
	@NotNull  // required
	@NotBlank // no empty strings
	@Size(min = 3, max = 30)  // reasonable length plz
	private String colorName;  // like "Blue", "Red" etc

	// todo: maybe add validation msg?
}
