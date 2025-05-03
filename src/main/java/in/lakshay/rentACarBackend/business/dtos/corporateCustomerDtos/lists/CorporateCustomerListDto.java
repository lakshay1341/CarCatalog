package in.lakshay.rentACarBackend.business.dtos.corporateCustomerDtos.lists;

import in.lakshay.rentACarBackend.business.dtos.customerDtos.lists.CustomerListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing corporate customers
// used in getAll() responses
@Data // lombok annotations r nice
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerListDto extends CustomerListDto { // extends base customer dto

    // corp specific fields for list view
    private String companyName; // company name
    private String taxNumber;   // tax id

    // might need to add more fields later

}
