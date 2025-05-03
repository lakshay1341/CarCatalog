package in.lakshay.rentACarBackend.business.dtos.userDtos.gets;

// dto for returning user info to client
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // lombok ftw - saves so much boilerplate
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDto {

    private int userId;  // primary key
    private String email;  // user's email - used for login

    // might need to add more fields later like name, role, etc
}