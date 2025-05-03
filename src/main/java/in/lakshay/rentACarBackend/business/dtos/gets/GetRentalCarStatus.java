package in.lakshay.rentACarBackend.business.dtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for rental status - shows current state of a rental
// used in the admin dashboard mostly
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalCarStatus {

    // rental identifiers
    private int rentalCarId;  // pk in db
    private LocalDate startDate;    // when rented
    private LocalDate finishDate;   // when due back

    // car info
    private int carId;        // which car
    private String brandName; // make
    private String colorName; // color

    // mileage tracking
    private Integer rentedKilometer;     // starting odo
    private Integer deliveredKilometer;  // ending odo (null if not returned)

    // location tracking
    private int rentedCityId;        // where picked up - id
    private String rentedCityName;   // where picked up - name
    private int deliveredCityId;     // where returned - id
    private String deliveredCityName; // where returned - name

    // customer info
    private String email;  // for notifications

    // todo: add status field? (active/returned/overdue)

}
