package in.lakshay.rentACarBackend.business.dtos.invoiceDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for getting corporate invoice by id
// extends the base invoice dto with company stuff
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerInvoiceByInvoiceIdDto extends GetInvoiceDto {

    private String companyName;  // legal name of business
    private String taxNumber;    // tax id for accounting

    // todo: maybe add more fields later if needed?

}