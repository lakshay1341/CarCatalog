package in.lakshay.rentACarBackend.business.requests.update;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// date handling
import java.time.LocalDate;

// request model for updating crash/accident records
// used when details change or estimates are updated
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarCrashRequest {

    // which crash record to update
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int carCrashId;  // pk in db

    // updated crash date
    @NotNull  // required
    private LocalDate crashDate;  // when it happened

    // updated damage estimate
    @NotNull  // required
    @Min(1)   // must cost something to fix
    private double crashValuation;  // repair cost

    // which car - shouldn't change but included
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int carId;  // fk to cars table

    // todo: maybe add description field?
    // also should add who was driving

}
