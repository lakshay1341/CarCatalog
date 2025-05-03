package in.lakshay.rentACarBackend.business.dtos.carCrashDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// yet another dto that's basically identical to the others
// srsly need to refactor these someday
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCrashListByCarIdDto {

    private int carCrashId;  // id
    private LocalDate crashDate;  // when it happened
    private double crashValuation;  // $$$

    // this is redundant since we're already filtering by car id
    // but keeping it for consistency w/ other DTOs
    private int carId;

}
