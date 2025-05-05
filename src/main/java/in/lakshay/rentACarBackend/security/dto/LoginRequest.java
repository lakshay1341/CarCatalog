package in.lakshay.rentACarBackend.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request DTO for login
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
