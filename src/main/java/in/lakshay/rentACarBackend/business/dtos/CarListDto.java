package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for car listings - includes color and brand info
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {

	private int carId;  // primary key
	private double dailyPrice;  // in rupees
	private int modelYear;   // year of manufacture
	private int kilometer;  // odometer reading

	// color info
	private int colorId;
	private String colorName;

	// brand info
	private int brandId;
	private String brandName;  // eg Honda, Toyota etc

}
