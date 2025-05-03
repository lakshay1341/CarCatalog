package in.lakshay.rentACarBackend.business.dtos.carMaintenanceDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

// dto for maintenance records in lists
// we have like 3 almost identical dtos for this... ugh
// should really consolidate these someday
@Data // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto {

    // maintenance stuff
    private int maintenanceId;  // pk
    private String description;  // what's broken
    private LocalDate returnDate;  // when it'll be fixed (hopefully)

    // car details - so we know what we're fixing
    private int carId;  // which car
    private double dailyPrice;  // how much $ we're losing per day lol
    private int kilometer;  // current odo reading
    private String brandName;  // make (toyota etc)
    private String colorName;  // color (red, blue etc)

    // todo: add status field? (scheduled/in-progress/completed)
    // also maybe add cost estimate?

}
