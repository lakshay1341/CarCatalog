package in.lakshay.rentACarBackend.business.dtos;

import lombok.Data;

// base customer dto - extends user dto
// empty for now but might add customer-specific stuff later
@Data
public class GetCustomerDto extends GetUserDto {
    // todo: maybe add loyalty status or smth?
    // could track rental history count here too
}
