package in.lakshay.rentACarBackend.business.dtos.carDtos.lists;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for cars filtered by price
// used when returning cars under a certain budget
@Data  // lombok is a lifesaver
@AllArgsConstructor
@NoArgsConstructor
public class CarListByDailyPriceDto {

	// car details
	private int carId;  // pk
	private double dailyPrice;  // price per day
	private int modelYear;  // year
	private int kilometer;  // mileage

	// just names, not ids - simpler than other DTOs
	private String colorName;  // color
	private String brandName;  // manufacturer

	// note: this one doesn't include colorId/brandId unlike the other DTOs
	// inconsistent design... should fix this someday
}
