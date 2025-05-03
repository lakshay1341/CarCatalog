package in.lakshay.rentACarBackend.business.dtos.carCrashDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for getting car crash details
// lombok makes this so much easier
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarCrashDto {

    private int carCrashId;  // id of the crash record
    private LocalDate crashDate;  // when it happened
    private double crashValuation;  // cost of damage
    private int carId;  // which car got damaged

}
