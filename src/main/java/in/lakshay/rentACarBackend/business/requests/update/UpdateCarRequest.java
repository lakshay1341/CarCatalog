package in.lakshay.rentACarBackend.business.requests.update;

// validation stuff
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// lombok makes life easier
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request model for updating car details
// pretty much same as create but with id field
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

	// which car to update
	@NotNull  // cant be null
	@Min(1)   // must be valid id
	private int carId;  // pk in db

	// updated price
	@NotNull
	@Min(1)  // no free cars!
	private double dailyPrice;  // rental cost per day

	// updated model year
	@NotNull
	@Min(1900)  // no antiques lol
	private int modelYear;  // manufacture year

	// updated mileage
	@NotNull
	@Min(0)  // cant be negative
	private int kilometer;  // current odometer reading

	// updated description - still optional
	private String description;  // notes about the car

	// updated brand
	@NotNull
	@Min(1)  // valid brand id
	private int brandId;  // fk to brands table

	// updated color
	@NotNull
	@Min(1)  // valid color id
	private int colorId;  // fk to colors table

	// todo: maybe add audit fields?
	// like who updated it and when
}
