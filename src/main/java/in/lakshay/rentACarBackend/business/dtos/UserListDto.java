package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for user listings
// super basic - just id and email for now
@Data  // lombok ftw - saves so much boilerplate
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {

    private int userId;  // pk
    private String email;  // login identifier

    // todo: maybe add more fields later? like name or role
    // but this is fine for now i guess

}
