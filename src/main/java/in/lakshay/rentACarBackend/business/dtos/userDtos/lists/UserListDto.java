package in.lakshay.rentACarBackend.business.dtos.userDtos.lists;

// dto for user listings - just basic info
// used in getAll() responses
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // lombok ftw - saves so much boilerplate
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {

    private int userId;  // pk
    private String email;  // login identifier

    // todo: maybe add more fields later? like name or role
    // but this is fine for now i guess

}
