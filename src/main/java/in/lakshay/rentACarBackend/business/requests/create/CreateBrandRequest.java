package in.lakshay.rentACarBackend.business.requests.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request model for creating a new car brand
// pretty simple - just need the name
@Data  // lombok makes life easier
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {

	// validation to ensure we get a valid name
	@NotNull   // can't be null
	@NotBlank  // can't be empty or just spaces
	@Size(min = 3, max = 30)  // reasonable length
	private String brandName;  // like "Toyota" or "BMW"

	// todo: maybe add country of origin?
	// also could add a logo url for the UI

}
