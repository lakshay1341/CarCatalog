package in.lakshay.rentACarBackend.business.requests.carCrashRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// request obj for creating new crash records
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarCrashRequest {

    @NotNull  // cant be null obvs
    private LocalDate crashDate;  // when it happened

    @NotNull
    @Min(1)  // gotta be at least some damage
    private double crashValuation;  // how much it costs to fix

    @NotNull
    @Min(1)  // car ids start at 1
    private int carId;  // which car got smashed

}
