package in.lakshay.rentACarBackend.business.dtos.gets.carCrash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for crash info - need this for the accident reports
// lombok makes life so much easier!
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarCrashDto {

    private int carCrashId;  // pk for the crash record
    private LocalDate crashDate;  // when it happened

    private double crashValuation;  // how much damage in $$$
    private int carId;  // which car got wrecked

    // todo: maybe add location? or photos?

}
