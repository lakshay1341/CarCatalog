package in.lakshay.rentACarBackend.business.dtos.carDtos.gets;

// lombok annotations for boilerplate reduction
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for car details - used in getById endpoint
// used when returning a single car's full details
// simpler than the entity - just what the client needs
@Data // lombok magic - getters, setters, etc
@AllArgsConstructor // all args constructor
@NoArgsConstructor // default constructor
public class GetCarDto {

	// basic car info - the essentials
	private int carId;  // primary key - unique identifier
	private double dailyPrice;  // how much per day in local currency
	private int modelYear;  // year of manufacture (like 2022)
	private int kilometer;  // current odometer reading
	private String description;  // details about car - can be null

	// related entities - just the names for simplicity
	// we don't need the ids on the client side usually
	private String colorName;  // color name not id (like "Red")
	private String brandName;  // brand name not id (like "Toyota")

	// todo: maybe add image url field? would be nice for the UI
	// todo: add transmission type (auto/manual)?
}
