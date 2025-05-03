package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for sorted car results
// used when displaying cars in sorted order (by price, year, etc)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarSortedDto {

	// basic car info
	private int carId;  // unique id
	private double dailyPrice;  // rental rate
	private int modelYear;  // year made

	// more details
	private int kilometer;  // odometer reading
	private String colorName;  // what color it is
	private String brandName;  // what brand/make

	// todo: add rating field? customers like to sort by rating
	// also need to add availability status probably

}
