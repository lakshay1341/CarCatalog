package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for individual (non-corporate) customers
// extends the base customer dto with person-specific fields
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerDto extends GetCustomerDto {

    private String firstName;  // first name obvs
    private String lastName;   // family name

    private String nationalIdentity;  // govt ID number - needed for contracts

    // todo: maybe add birthdate? driver license info?

}