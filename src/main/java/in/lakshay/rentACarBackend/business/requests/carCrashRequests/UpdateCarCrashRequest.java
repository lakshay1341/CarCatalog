package in.lakshay.rentACarBackend.business.requests.carCrashRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// request for updating crash details
// pretty similar to create but w/ id
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarCrashRequest {

    @NotNull
    @Min(1)  // ids start at 1
    private int carCrashId;  // which crash to update

    @NotNull
    private LocalDate crashDate;  // when it happened

    @NotNull
    @Min(1)  // gotta cost something to fix
    private double crashValuation;  // repair cost

    @NotNull
    @Min(1)  // valid car id
    private int carId;  // which car

}
