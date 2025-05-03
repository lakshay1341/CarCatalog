package in.lakshay.rentACarBackend.business.requests.carCrashRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// simplest request - just need the id to delete
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarCrashRequest {

    // just need the id of the crash to delete
    @NotNull  // cant be null
    @Min(1)   // ids start at 1
    private int carCrashId;  // which crash to delete

}
