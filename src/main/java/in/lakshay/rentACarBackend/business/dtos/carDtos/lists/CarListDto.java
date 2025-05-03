package in.lakshay.rentACarBackend.business.dtos.carDtos.lists;

// lombok stuff - makes life easier
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// main dto for car listings in the system
// used for the basic getAll() endpoint
// this is the "base" car dto that others are based on
// we have way too many similar dtos tbh
@Data  // lombok magic - no getters/setters needed!
@AllArgsConstructor // all args constructor
@NoArgsConstructor // default constructor
public class CarListDto {

	// basic car info - the essentials
	private int carId;  // primary key - unique identifier
	private double dailyPrice;  // rental price per day
	private int modelYear;  // year of manufacture (like 2022)
	private int kilometer;  // current mileage/odometer reading

	// color info - both id and name for convenience
	private int colorId;  // foreign key to colors table
	private String colorName;  // actual color name like "Red"

	// brand info - both id and name for convenience
	private int brandId;  // foreign key to brands table
	private String brandName;  // manufacturer name like "Toyota"

	// TODO: add description? image url?
	// TODO: maybe add transmission type (auto/manual)?
}
