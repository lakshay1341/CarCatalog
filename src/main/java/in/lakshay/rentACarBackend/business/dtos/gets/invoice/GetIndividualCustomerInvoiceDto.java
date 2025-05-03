package in.lakshay.rentACarBackend.business.dtos.gets.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// invoice for regular people (not companies)
// adds personal info fields to the base invoice
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerInvoiceDto extends GetInvoiceDto {

    private String firstName;  // customer first name
    private String lastName;   // last name obvs

    private String nationalIdentity;  // id number - needed for some countries

    // hmm should we add address here? or is that in the parent class?

}
