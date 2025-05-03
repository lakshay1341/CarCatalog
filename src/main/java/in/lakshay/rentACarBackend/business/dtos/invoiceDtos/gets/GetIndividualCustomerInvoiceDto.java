package in.lakshay.rentACarBackend.business.dtos.invoiceDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// base dto for individual customer invoices
// used for regular people (not companies)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerInvoiceDto extends GetInvoiceDto {

    private String firstName;  // customer's first name
    private String lastName;   // family name

    private String nationalIdentity;  // ID number - needed for some countries

    // maybe add driver license info? might be useful for some reports

}
