package in.lakshay.rentACarBackend.business.dtos.invoiceDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// for individual customers (not companies)
// gets invoice by id
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerInvoiceByInvoiceIdDto extends GetInvoiceDto {

    private String firstName;  // person's first name
    private String lastName;   // surname

    private String nationalIdentity;  // govt ID number
    // hmm maybe should add phone number too?

}
