package in.lakshay.rentACarBackend.business.requests.userRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

// base request for updating users
// has all the common user fields
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    // which user to update
    @NotNull  // gotta have this
    @Min(1)   // valid ids only
    private int userId;  // pk

    // new email - must be valid format
    @NotNull
    @NotBlank
    @Email    // checks format
    private String email;  // also username

    // new password - basic validation
    // will be hashed with bcrypt before storing
    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)  // not too short/long
    private String password;

}
