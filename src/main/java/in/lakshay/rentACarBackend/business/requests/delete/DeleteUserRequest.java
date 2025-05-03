package in.lakshay.rentACarBackend.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// simple request for deleting users
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserRequest {

    // id must be positive - duh!
    @NotNull // can't be null obvs
    @Min(1)  // gotta be at least 1
    private int userId;  // the id of user to delete

    // thats it! just need the id

}
