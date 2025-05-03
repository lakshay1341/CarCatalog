package in.lakshay.rentACarBackend.business.requests.colorRequests;

// validation annotations
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// lombok stuff - saves typing
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request obj for adding new colors to the system
// used in POST /api/colors/add endpoint
@Data // lombok magic - getters, setters, etc
@AllArgsConstructor // ctor with all args
@NoArgsConstructor // default ctor
public class CreateColorRequest {

	// validation - name cant be null/empty and must be 3-30 chars
	// these annotations are processed by spring validation
	@NotNull // can't be null
	@NotBlank // can't be empty or just whitespace
	@Size(min = 3, max = 30)  // no crazy long color names plz
	private String colorName; // like "Red", "Blue", etc

	// TODO: maybe add hex code for UI later? would be nice for frontend
	// TODO: add color category (primary, metallic, etc)?
}
