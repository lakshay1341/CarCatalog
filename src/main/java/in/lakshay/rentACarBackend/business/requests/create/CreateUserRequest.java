package in.lakshay.rentACarBackend.business.requests.create;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation - import all cuz lazy
import jakarta.validation.constraints.*;

// request model for creating new users
// base class for all user types
@Data  // lombok is awesome
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    // email must be valid format
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Email  // must be valid email format
    private String email;  // also used as username

    // password with basic validation
    // todo: add better password rules
    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)  // reasonable length
    private String password;  // stored in plaintext for now - fix b4 prod!

    // should prob add name fields but those are in subclasses

}
