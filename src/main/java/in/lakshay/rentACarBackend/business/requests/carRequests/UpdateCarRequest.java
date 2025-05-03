package in.lakshay.rentACarBackend.business.requests.carRequests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request model for updating a car
// contains all fields that can be updated
@Data  // lombok rocks
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

	// which car to update
	@NotNull
	@Min(1)  // ids start at 1
	private int carId;  // pk in db

	// pricing info
	@NotNull
	@Min(1)  // can't be free or negative
	private double dailyPrice;  // per day rental cost

	// car details
	@NotNull
	@Min(1900)  // no antiques pls
	private int modelYear;  // year of manufacture

	@NotNull
	@Min(0)  // can't be negative
	private int kilometer;  // current odometer reading

	// optional description - can be null
	private String description;  // any notes about the car

	// foreign keys
	@NotNull
	@Min(1)
	private int brandId;  // make (Toyota, BMW, etc)

	@NotNull
	@Min(1)
	private int colorId;  // color (red, blue, etc)

	// todo: maybe add availability status?
	// also should add image urls at some point

}
