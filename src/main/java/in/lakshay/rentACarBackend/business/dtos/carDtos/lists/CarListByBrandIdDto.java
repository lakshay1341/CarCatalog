package in.lakshay.rentACarBackend.business.dtos.carDtos.lists;

// lombok ftw
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing cars filtered by brand
// used when returning cars of a specific brand
@Data // lombok is awesome
@AllArgsConstructor
@NoArgsConstructor
public class CarListByBrandIdDto {

	// car details
	private int carId;  // pk
	private double dailyPrice;  // rental cost
	private int modelYear;  // year
	private int kilometer;  // mileage

	// color info - both id and name
	private int colorId;  // fk to colors table
	private String colorName;  // actual color name

	// brand info - kinda redundant since we filtered by brand already
	// but keeping for consistency w/ other DTOs
	private int brandId;  // fk to brands table
	private String brandName;  // manufacturer name

	// so many DTOs that are almost identical... need to clean this up someday
}
