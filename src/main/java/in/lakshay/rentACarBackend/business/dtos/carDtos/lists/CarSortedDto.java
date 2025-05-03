package in.lakshay.rentACarBackend.business.dtos.carDtos.lists;

// lombok annotations
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for sorted car results
// pretty much identical to CarPagedDto - kinda redundant tbh
// should probably refactor these someday
@Data  // saves typing all those getters/setters
@AllArgsConstructor
@NoArgsConstructor
public class CarSortedDto {

	// car info
	private int carId;  // primary key
	private double dailyPrice;  // price per day
	private int modelYear;  // model year
	private int kilometer;  // odometer reading

	// names not ids - easier for frontend
	private String colorName;  // car color
	private String brandName;  // manufacturer

	// this is basically a copy-paste of CarPagedDto lol
}
