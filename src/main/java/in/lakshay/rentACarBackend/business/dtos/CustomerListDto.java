package in.lakshay.rentACarBackend.business.dtos;

import lombok.Data;

// dto for customer list - extends user list dto
// nothing special here, just inheritance
@Data
public class CustomerListDto extends UserListDto {
    // empty for now - just inherits from UserListDto
    // might add customer-specific fields later

    // todo: maybe add loyalty tier? (silver/gold/platinum)
}
