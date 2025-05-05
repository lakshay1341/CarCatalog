package in.lakshay.rentACarBackend.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request DTO for registration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    // Add more fields as needed for your user registration
    // For example, firstName, lastName, etc.
}
