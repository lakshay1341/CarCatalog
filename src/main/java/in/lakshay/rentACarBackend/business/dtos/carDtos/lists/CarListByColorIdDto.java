package in.lakshay.rentACarBackend.business.dtos.carDtos.lists;

// lombok annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing cars filtered by color
// literally a copy-paste of CarListByBrandIdDto lol
@Data // no need to write getters/setters
@AllArgsConstructor
@NoArgsConstructor
public class CarListByColorIdDto {

	// car info
	private int carId;  // primary key
	private double dailyPrice;  // daily rental price
	private int modelYear;  // year of manufacture
	private int kilometer;  // current mileage

	// color info - redundant since we filtered by color
	// but keeping for consistency
	private int colorId;  // fk to colors
	private String colorName;  // actual color name

	// brand info
	private int brandId;  // fk to brands
	private String brandName;  // manufacturer name

	// TODO: srsly need to refactor all these nearly identical DTOs
	// maybe use inheritance or something?
}
