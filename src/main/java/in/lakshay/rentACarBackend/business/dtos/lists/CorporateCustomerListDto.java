package in.lakshay.rentACarBackend.business.dtos.lists;

import in.lakshay.rentACarBackend.business.dtos.CustomerListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for corporate customers in lists
// extends base customer with business fields
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerListDto extends CustomerListDto {

    private String companyName;  // name of business
    private String taxNumber;    // tax id for invoicing

    // note: should probably add company size or fleet size
    // would help with reporting and sales targeting

}
