package in.lakshay.rentACarBackend.business.dtos.carCrashDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for listing car crashes
// pretty much same as GetCarCrashDto but in a diff package
// maybe should refactor this someday?
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCrashListDto {

    private int carCrashId;  // crash id
    private LocalDate crashDate;  // when
    private double crashValuation;  // how much $$$
    private int carId;  // which car

}
