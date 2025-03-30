package in.lakshay.rentACarBackend.business.dtos.gets;

import in.lakshay.rentACarBackend.business.dtos.GetCustomerDto;
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
