package in.lakshay.rentACarBackend.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request for deleting maintenance records
// super simple - just need the id
@Data // lombok magic
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarMaintenanceRequest {

    // which record to delete
    @NotNull // can't be null
    @Min(1)  // must be valid id
    private int maintenanceId;  // pk to delete

    // todo: maybe add confirmation field?
    // would help prevent accidental deletions

}
