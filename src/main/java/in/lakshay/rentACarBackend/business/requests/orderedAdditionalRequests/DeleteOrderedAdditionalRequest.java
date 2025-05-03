package in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request model for removing extras from a rental
// super simple - just need the id
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderedAdditionalRequest {

    // which ordered extra to remove
    @NotNull  // required
    @Min(1)   // valid id only
    private int orderedAdditionalId;  // pk

    // todo: maybe add reason for removal?
    // could be useful for refund processing

}
