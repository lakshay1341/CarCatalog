package in.lakshay.rentACarBackend.business.dtos.invoiceDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for getting individual customer invoice by invoice number
// extends base invoice with person-specific fields
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerInvoiceByInvoiceNoDto extends GetInvoiceDto {

    private String firstName;  // first name
    private String lastName;   // last name

    private String nationalIdentity;  // govt ID - needed for some countries

    // hmm should we add phone number too? might be useful

}
