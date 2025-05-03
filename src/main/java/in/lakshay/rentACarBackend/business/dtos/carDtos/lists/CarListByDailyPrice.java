package in.lakshay.rentACarBackend.business.dtos.carDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// used for listing cars sorted by price
// might need to add more filtering options later
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListByDailyPrice {

	private int carId;  // unique id
	private double dailyPrice;  // rental price per day
	private int modelYear;   // year of manufacture
	private int kilometer;  // odometer reading
	private String colorName;  // color of car
	private String brandName;  // make of the car

}
