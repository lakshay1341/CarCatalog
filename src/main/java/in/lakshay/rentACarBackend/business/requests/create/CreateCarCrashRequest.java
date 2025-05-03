package in.lakshay.rentACarBackend.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// request model for recording car crashes/accidents
// used when a rental car gets damaged
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarCrashRequest {

    // when did it happen
    @NotNull  // gotta have a date
    private LocalDate crashDate;

    // how much damage in $$$
    @NotNull
    @Min(1)  // has to cost something to fix
    private double crashValuation;

    // which car got damaged
    @NotNull
    @Min(1)  // valid car id
    private int carId;  // fk to cars table

    // todo: maybe add description field for details?
    // also should prob add who was driving

}
