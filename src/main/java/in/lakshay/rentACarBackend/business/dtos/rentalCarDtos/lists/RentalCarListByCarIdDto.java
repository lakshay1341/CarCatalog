package in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for listing rentals by car id
// used when we need to see all rentals for a specific car
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarListByCarIdDto {

    // basic rental info
    private int rentalCarId;  // rental id in db
    private LocalDate startDate;  // when they got the car
    private LocalDate finishDate; // when they should return it

    // car info
    private int carId;  // which car was rented
    private String brandName;  // make of the car
    private String colorName;  // color of car

    // odometer readings
    private Integer rentedKilometer;  // km when rented out
    private Integer deliveredKilometer;  // km when returned (null if still out)

    // location tracking
    private int rentedCityId;  // where they picked up
    private String rentedCityName;  // city name for pickup
    private int deliveredCityId;  // where they returned it
    private String deliveredCityName;  // city name for return

    // customer info
    private String email;  // contact email

    // todo: add payment status? might be useful
}
