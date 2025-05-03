package in.lakshay.rentACarBackend.business.dtos.corporateCustomerDtos.gets;

import in.lakshay.rentACarBackend.business.dtos.customerDtos.gets.GetCustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for getting corporate customer details
// used for API responses
@Data // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerDto extends GetCustomerDto { // inherits base customer fields

    // corp specific fields
    private String companyName; // name of the company
    private String taxNumber;   // tax id - 10 digits

    // TODO: maybe add more fields like industry, size, etc?

}
