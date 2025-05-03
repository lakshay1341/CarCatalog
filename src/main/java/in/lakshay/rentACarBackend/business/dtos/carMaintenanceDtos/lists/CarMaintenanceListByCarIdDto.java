package in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// yet another dto that's basically identical to CarMaintenanceListDto
// we rly need to refactor these at some point
// too many duplicate dtos floating around
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListByCarIdDto {

    private int maintenanceId;  // id
    private String description;  // what's wrong
    private LocalDate returnDate;  // when fixed

    // car info - kinda redundant since we're filtering by car id already
    // but keeping for consistency w/ other DTOs
    private int carId;  // which car
    private double dailyPrice;  // rental rate
    private int kilometer;  // mileage
    private String brandName;  // make
    private String colorName;  // color

}
