package in.lakshay.rentACarBackend.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

// request for updating maintenance records
// used when details change or return date is known
@Data // lombok magic
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {

    // which maintenance record to update
    @NotNull
    @Min(1) // must be valid id
    private int maintenanceId;  // pk

    // updated description
    @NotNull
    @NotBlank // no empty strings plz
    @Size(min = 3, max = 300) // keep it reasonable
    private String description;  // what's being done

    // when car will be back - still optional
    private LocalDate returnDate;  // might be updated if we know now

    // which car - shouldn't change but included anyway
    @NotNull
    @Min(1) // valid car id
    private int carId;  // fk to cars table

    // todo: maybe add status field? in-progress/completed/etc

}
