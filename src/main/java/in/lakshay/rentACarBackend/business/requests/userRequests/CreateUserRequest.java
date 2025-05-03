package in.lakshay.rentACarBackend.business.requests.userRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

// base class for user creation
// has the basic stuff every user needs
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    // gotta have valid email format
    // this is checked by @Email annotation
    @NotNull
    @NotBlank
    @Email
    private String email;

    // password - keep it reasonable length
    // todo: maybe add more password validation later?
    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)  // min 3 chars, max 30
    private String password;

}
