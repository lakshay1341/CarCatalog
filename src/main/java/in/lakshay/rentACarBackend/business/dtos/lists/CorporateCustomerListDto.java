package in.lakshay.rentACarBackend.business.dtos.lists;

import in.lakshay.rentACarBackend.business.dtos.CustomerListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerListDto extends CustomerListDto {

    private String companyName;
    private String taxNumber;

}
