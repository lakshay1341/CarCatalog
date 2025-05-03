package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for cars filtered by price
// used for budget search results
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListByDailyPrice {

	// basic car info
	private int carId;       // unique id
	private double dailyPrice;  // rental cost per day
	private int modelYear;   // year of manufacture
	private int kilometer;   // current mileage

	// descriptive stuff
	private String colorName;  // color (red, blue, etc)
	private String brandName;  // make (toyota, etc)

	// todo: maybe add availability status?
	// also should add image url probably

}
