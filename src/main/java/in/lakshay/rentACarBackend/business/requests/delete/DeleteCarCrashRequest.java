package in.lakshay.rentACarBackend.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// simple request class for deleting car crash records
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarCrashRequest {

    // gotta be at least 1, duh
    @NotNull
    @Min(1)
    private int carCrashId;

}
