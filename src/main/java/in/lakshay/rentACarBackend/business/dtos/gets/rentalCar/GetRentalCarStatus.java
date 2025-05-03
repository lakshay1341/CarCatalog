package in.lakshay.rentACarBackend.business.dtos.gets.rentalCar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for rental status - used for tracking active rentals
// has all the info needed for the rental status screen
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalCarStatus {

    // basic rental info
    private int rentalCarId;  // pk
    private LocalDate startDate;    // when they picked up
    private LocalDate finishDate;   // when they should return

    // car details
    private int carId;        // which car
    private String brandName; // make (toyota, etc)
    private String colorName; // color (red, blue, etc)

    // mileage tracking
    private Integer rentedKilometer;     // odo reading at pickup
    private Integer deliveredKilometer;  // odo at return (null if not returned yet)

    // location info - where rented vs returned
    private int rentedCityId;      // pickup location id
    private String rentedCityName; // pickup location name
    private int deliveredCityId;      // dropoff location id
    private String deliveredCityName;  // dropoff location name

    // customer contact
    private String email;  // for notifications

    // todo: maybe add status enum? (active, completed, overdue)

}
