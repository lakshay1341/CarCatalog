package in.lakshay.rentACarBackend.business.dtos.lists.corporateCustomer;

import in.lakshay.rentACarBackend.business.dtos.CustomerListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing corporate customers
// extends the base customer dto with company-specific fields
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerListDto extends CustomerListDto {

    private String companyName;  // business name
    private String taxNumber;    // tax id for billing

    // todo: maybe add credit limit? would be useful for corporate accounts
    // also need to add contact person name at some point

}
