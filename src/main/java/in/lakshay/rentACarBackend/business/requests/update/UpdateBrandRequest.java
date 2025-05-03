package in.lakshay.rentACarBackend.business.requests.update;

// validation annotations
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// lombok makes coding less tedious
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request model for updating a brand
// pretty simple - just need id and new name
@Data  // lombok saves typing
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {

	// which brand to update
	@NotNull  // cant be null
	@Min(1)   // must be valid id
	private int brandId;  // pk in db

	// new name for the brand
	@NotNull   // required
	@NotBlank  // cant be empty
	@Size(min = 3, max = 30)  // reasonable length
	private String brandName;  // like "Toyota" or "BMW"

	// todo: maybe add audit fields?
	// like who updated it and when
}
