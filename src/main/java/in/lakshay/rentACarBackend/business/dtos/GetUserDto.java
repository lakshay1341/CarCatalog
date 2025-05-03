package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for user info - basic stuff
// used for API responses
@Data  // lombok ftw - saves so much boilerplate
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDto {

    private int userId;  // primary key
    private String email;  // login identifier

    // might need to add more fields later like name, role, etc
    // but this is fine for now
}