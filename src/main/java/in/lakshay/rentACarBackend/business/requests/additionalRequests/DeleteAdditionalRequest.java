package in.lakshay.rentACarBackend.business.requests.additionalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request model for deleting additional services
// super simple - just need the id
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAdditionalRequest {

    // which one to delete
    @NotNull  // must provide id
    @Min(1)   // must be valid
    private int additionalId;  // pk to delete

    // thats it! simplest request model

}
