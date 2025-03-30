package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerDto  extends GetCustomerDto{

    private String firstName;
    private String lastName;
    private String nationalIdentity;

}