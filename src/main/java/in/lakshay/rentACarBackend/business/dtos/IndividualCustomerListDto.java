package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing individual customers
// extends CustomerListDto to get all the base fields
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerListDto extends CustomerListDto {

    private String firstName;  // person's first name
    private String lastName;   // family name
    private String nationalIdentity;  // govt ID - needed for contracts

    // hmm should we add phone number too? might be useful
    // also maybe add a getFullName() helper?

}