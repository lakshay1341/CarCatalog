package in.lakshay.rentACarBackend.business.dtos.lists.carCrash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for listing car crashes
// used in accident reports and insurance claims
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCrashListDto {

    private int carCrashId;  // pk for the crash record
    private LocalDate crashDate;  // when it happened
    private double crashValuation;  // cost of damage in $$$
    private int carId;  // which car got damaged

    // todo: maybe add location or driver info?
    // would be useful for insurance reports
}
