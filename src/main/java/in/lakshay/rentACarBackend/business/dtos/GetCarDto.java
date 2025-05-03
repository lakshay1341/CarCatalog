package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for car details - used in getById endpoint
// used when returning a single car's full details
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarDto {

	// basic car info
	private int carId;  // pk
	private double dailyPrice;  // rental cost
	private int modelYear;  // year made
	private int kilometer;  // current mileage
	private String description;  // any notes

	// descriptive stuff
	private String colorName;  // color (red, blue, etc)
	private String brandName;  // make (toyota, etc)

	// todo: add image urls?
	// also need to add availability status

}
