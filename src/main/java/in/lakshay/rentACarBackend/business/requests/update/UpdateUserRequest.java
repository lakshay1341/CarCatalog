package in.lakshay.rentACarBackend.business.requests.update;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation - import all cuz lazy
import jakarta.validation.constraints.*;

// request model for updating user details
// base class for all user types
@Data  // lombok is awesome
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    // which user to update
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int userId;  // pk in db

    // updated email
    @NotNull  // required
    @NotBlank  // cant be empty
    @Email  // must be valid format
    private String email;  // also used as username

    // updated password
    // todo: add better password rules
    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)  // reasonable length
    private String password;  // still stored in plaintext - fix b4 prod!

    // todo: maybe add password confirmation field?
    // also should add old password for verification

}
