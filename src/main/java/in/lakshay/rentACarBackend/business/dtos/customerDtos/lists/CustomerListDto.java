package in.lakshay.rentACarBackend.business.dtos.customerDtos.lists;

import in.lakshay.rentACarBackend.business.dtos.userDtos.lists.UserListDto;
import lombok.Data;

// dto for listing customers
// extends UserListDto to get all user fields
@Data
public class CustomerListDto extends UserListDto {
    // empty for now, but we might need to add customer-specific stuff
    // like rental history count or smth
}
