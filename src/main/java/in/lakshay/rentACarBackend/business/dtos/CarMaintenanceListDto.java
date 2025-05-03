package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

// dto for maintenance records list
// used in the maintenance dashboard
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto {

    // maintenance details
    private int maintenanceId;   // pk
    private String description;  // whats wrong with it
    private LocalDate returnDate;  // when it'll be back in service

    // car info
    private int carId;  // which car
    private double dailyPrice;  // how much we're losing per day lol
    private int kilometer;  // current odo reading

    // descriptive stuff
    private String brandName;  // make
    private String colorName;  // color

    // todo: add maintenance status (scheduled/in-progress/completed)
    // also need to add mechanic contact info maybe?

}
