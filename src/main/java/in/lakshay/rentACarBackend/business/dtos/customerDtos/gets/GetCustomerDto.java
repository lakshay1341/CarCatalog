package in.lakshay.rentACarBackend.business.dtos.customerDtos.gets;

import in.lakshay.rentACarBackend.business.dtos.userDtos.gets.GetUserDto;
import lombok.Data;

// customer dto that extends user dto
// empty for now but might add customer-specific fields later
@Data
public class GetCustomerDto extends GetUserDto {
    // todo: add customer specific fields if needed
    // maybe loyalty points or smth?
}
