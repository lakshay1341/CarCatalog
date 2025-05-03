package in.lakshay.rentACarBackend.business.dtos.gets;

import in.lakshay.rentACarBackend.business.dtos.GetCustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for corporate customers
// extends base customer with business-specific fields
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerDto extends GetCustomerDto {

    private String companyName;  // legal business name
    private String taxNumber;    // tax id for invoicing

    // todo: maybe add industry type? size? credit terms?

}
