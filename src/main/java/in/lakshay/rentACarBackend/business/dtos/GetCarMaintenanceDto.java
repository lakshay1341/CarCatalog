package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for getting car maintenance details
// includes car info too for convenience
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarMaintenanceDto {

    // maintenance details
    private int maintenanceId;  // pk
    private String description;  // whats wrong with it
    private LocalDate returnDate;  // when it'll be back

    // car details
    private int carId;  // which car
    private int kilometer;  // current mileage
    private String brandName;  // make
    private String colorName;  // color

    // todo: add mechanic contact info?
    // also need to add cost estimate probably

}
