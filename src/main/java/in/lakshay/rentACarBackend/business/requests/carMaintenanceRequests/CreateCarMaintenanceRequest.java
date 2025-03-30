package in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 300)
    private String description;

    private LocalDate returnDate;

    @NotNull
    @Min(1)
    private int carId;

}
