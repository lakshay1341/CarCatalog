package in.lakshay.rentACarBackend.business.dtos.gets.corporateCustomer;

import in.lakshay.rentACarBackend.business.dtos.GetCustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// for corporate clients - extends the base customer dto
// cuz companies need extra fields
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerDto extends GetCustomerDto {

    private String companyName;  // legal name of company
    private String taxNumber;    // tax id for billing

    // might need to add contact person later?

}
