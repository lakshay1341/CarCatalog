package in.lakshay.rentACarBackend.business.dtos.carDtos.lists;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for paginated car results
// used when returning pages of cars
@Data  // lombok magic - no getters/setters needed
@AllArgsConstructor
@NoArgsConstructor
public class CarPagedDto {

	// basic car info
	private int carId;  // pk
	private double dailyPrice;  // cost per day
	private int modelYear;  // year of manufacture
	private int kilometer;  // current odometer

	// related entities - just names, not ids
	private String colorName;  // like "Red", "Blue", etc
	private String brandName;  // like "Toyota", "Honda", etc

	// todo: maybe add description field? or image url?
}
