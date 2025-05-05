package in.lakshay.rentACarBackend.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// response DTO for authentication
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    
    private String accessToken;
    private String tokenType = "Bearer";
    private int userId;
    private String email;
    
    public AuthResponse(String accessToken, int userId, String email) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.email = email;
    }
}
