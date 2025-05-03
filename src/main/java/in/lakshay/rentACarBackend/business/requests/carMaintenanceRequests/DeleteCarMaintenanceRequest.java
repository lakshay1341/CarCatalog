package in.lakshay.rentACarBackend.business.requests.carMaintenanceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// simplest request - just need the id to delete
// car is back from the shop, maintenance record no longer needed
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarMaintenanceRequest {

    // just need the id of the maintenance record to delete
    @NotNull  // cant be null
    @Min(1)   // ids start at 1
    private int maintenanceId;  // which maintenance record to delete

}
