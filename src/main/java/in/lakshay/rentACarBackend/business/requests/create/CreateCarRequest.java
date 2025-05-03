package in.lakshay.rentACarBackend.business.requests.create;

// validation stuff
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// lombok makes life easier!
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request obj for creating new cars
// used in POST /api/cars/add endpoint
@Data // lombok magic - getters, setters, etc
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {

	// price must be positive - no free cars!
	@NotNull // cant be null
	@Min(1) // at least 1 unit of currency
	private double dailyPrice; // rental price per day

	// car must be made after 1900 lol - no antiques
	@NotNull
	@Min(1900) // reasonable minimum year
	private int modelYear; // manufacture year

	// optional description - no validation needed
	private String description; // details about the car

	// km reading - can be 0 for new car
	@Min(0) // cant be negative
	@NotNull
	private int kilometer; // odometer reading

	// must have valid brand - foreign key
	@NotNull
	@Min(1)  // ids start at 1 in our db
	private int brandId; // reference to brands table

	@NotNull
	@Min(1)  // ids start at 1 in our db
	private int colorId; // reference to colors table

	// TODO: maybe add image urls later?
	// TODO: add transmission type (auto/manual)?
	// also should prob add fuel type (gas/diesel/electric)?
}
