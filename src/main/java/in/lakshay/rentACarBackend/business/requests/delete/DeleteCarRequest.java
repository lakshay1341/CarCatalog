package in.lakshay.rentACarBackend.business.requests.delete;

// validation stuff
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// lombok makes life easier
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request model for deleting a car
// simplest possible - just need the id
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarRequest {

	// which car to delete
	@NotNull  // cant be null
	@Min(1)   // must be valid id
	private int carId;  // pk in db

	// todo: maybe add a safety check field?
	// like "confirmDelete" boolean to prevent accidents
	// also should check if car is currently rented before deleting!
}
