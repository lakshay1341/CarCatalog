package in.lakshay.rentACarBackend.business.requests.userRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// another delete user request? weird, we have a duplicate
// in the delete package too. should probably consolidate these
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserRequest {

    // user id to delete
    @NotNull  // not null plz
    @Min(1)   // positive ids only
    private int userId;  // primary key

}
