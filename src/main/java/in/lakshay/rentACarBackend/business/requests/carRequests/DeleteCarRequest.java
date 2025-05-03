package in.lakshay.rentACarBackend.business.requests.carRequests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request model for deleting a car
// just need the id to delete it
@Data  // lombok is awesome
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarRequest {

	// validation to ensure we get a valid id
	@NotNull  // can't be null duh
	@Min(1)   // ids start at 1 in our system
	private int carId;  // which car to delete

	// todo: maybe add a safety check field?
	// like "confirmDelete" boolean to prevent accidents
	// also should check if car is currently rented before deleting!

}
