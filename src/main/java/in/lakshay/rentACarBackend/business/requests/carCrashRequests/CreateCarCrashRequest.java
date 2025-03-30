package in.lakshay.rentACarBackend.business.requests.carCrashRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarCrashRequest {

    @NotNull
    private LocalDate crashDate;

    @NotNull
    @Min(1)
    private double crashValuation;

    @NotNull
    @Min(1)
    private int carId;

}
