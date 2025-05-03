package in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

// request for updating maintenance details
// pretty similar to create but w/ id
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {

    @NotNull
    @Min(1)  // ids start at 1
    private int maintenanceId;  // which maintenance record to update

    @NotNull
    @NotBlank  // gotta have some description
    @Size(min = 3, max = 300)  // not too long or short
    private String description;  // what's wrong with the car

    // when it'll be fixed - can be null if unknown
    private LocalDate returnDate;  // might be null if we dont know yet

    @NotNull
    @Min(1)  // valid car id
    private int carId;  // which car is being fixed

}
