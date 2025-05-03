package in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for rental status - shows current state of a rental
// used mostly for admin dashboard stuff
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalCarStatus {

    // rental info
    private int rentalCarId;  // pk in db
    private LocalDate startDate;    // when rented
    private LocalDate finishDate;   // when due back

    // car details
    private int carId;        // which car
    private String brandName; // make
    private String colorName; // color

    // mileage tracking
    private Integer rentedKilometer;     // starting odo
    private Integer deliveredKilometer;  // ending odo (null if not returned)

    // location info
    private int rentedCityId;        // pickup location id
    private String rentedCityName;   // pickup location name
    private int deliveredCityId;     // dropoff location id
    private String deliveredCityName; // dropoff location name

    // customer contact
    private String email;  // for notifications

    // todo: maybe add status enum? (active/returned/overdue)
    // also should prob add payment status here too

}
