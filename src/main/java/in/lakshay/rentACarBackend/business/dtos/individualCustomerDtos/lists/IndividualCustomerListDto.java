package in.lakshay.rentACarBackend.business.dtos.individualCustomerDtos.lists;

import in.lakshay.rentACarBackend.business.dtos.customerDtos.lists.CustomerListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing individual customers
// used in the customer list screen
// extends base customer list with person fields
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerListDto extends CustomerListDto {

    private String firstName;  // person's first name
    private String lastName;   // surname/family name

    private String nationalIdentity;  // natl ID - maybe mask this for privacy?

    // hmm should we add a fullName getter? would be convenient
    // but lombok doesn't generate that automatically

}