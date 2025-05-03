package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for paginated car results
// used when we need to show cars in pages
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarPagedDto {

	// car identifiers
	private int carId;  // pk
	private double dailyPrice;  // cost per day
	private int modelYear;  // manufacture yr

	// car details
	private int kilometer;  // current mileage
	private String colorName;  // color
	private String brandName;  // make

	// hmm should probably add availability flag here
	// and maybe a thumbnail image url?

}
