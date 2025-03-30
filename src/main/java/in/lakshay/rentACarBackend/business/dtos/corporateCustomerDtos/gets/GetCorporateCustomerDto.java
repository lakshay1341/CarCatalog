package in.lakshay.rentACarBackend.business.dtos.corporateCustomerDtos.gets;

import in.lakshay.rentACarBackend.business.dtos.customerDtos.gets.GetCustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerDto extends GetCustomerDto {

    private String companyName;
    private String taxNumber;

}
