package in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for getting single maintenance record details
// notice how similar this is to the list dto... sigh
@Data // lombok is a lifesaver
@AllArgsConstructor
@NoArgsConstructor
public class GetCarMaintenanceDto {

    // maintenance info
    private int maintenanceId;  // pk
    private String description;  // what needs fixing
    private LocalDate returnDate;  // when it should be done

    // car details - basic info about the car being fixed
    private int carId;  // which car
    private int kilometer;  // current mileage
    private String brandName;  // make (toyota etc)
    private String colorName;  // color (blue, red, etc)

    // note: this one doesn't have dailyPrice unlike the list dto
    // inconsistent design... ugh

}
