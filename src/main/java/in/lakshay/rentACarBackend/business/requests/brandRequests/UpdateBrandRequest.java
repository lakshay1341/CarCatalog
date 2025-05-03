package in.lakshay.rentACarBackend.business.requests.brandRequests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request model for updating a brand
// need both id and new name
@Data   // lombok saves so much typing!
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {

	// which brand to update
	@NotNull  // can't be null
	@Min(1)   // must be positive
	private int brandId;  // pk in db

	// new name for the brand
	@NotNull   // can't be null
	@NotBlank  // can't be empty
	@Size(min = 3, max = 30)  // reasonable length
	private String brandName;  // like "Toyota" or "BMW"

	// todo: maybe add audit fields? like who updated it?

}
