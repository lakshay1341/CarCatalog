package in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

// request obj for creating new maintenance records
// when cars need to go to the shop
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Size(min = 3, max = 300)  // reasonable length
    private String description;  // what's wrong with the car

    // when car will be back - can be null if unknown
    private LocalDate returnDate;  // might be null if we dont know yet

    @NotNull
    @Min(1)  // ids start at 1
    private int carId;  // which car needs fixing

}
