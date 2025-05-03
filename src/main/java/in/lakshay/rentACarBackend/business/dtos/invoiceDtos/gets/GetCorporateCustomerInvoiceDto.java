package in.lakshay.rentACarBackend.business.dtos.invoiceDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// base dto for corporate customer invoices
// used by other more specific invoice dtos
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerInvoiceDto extends GetInvoiceDto {

    private String companyName;  // company name for invoice
    private String taxNumber;    // tax id - required for business invoices

    // todo: maybe add company address? or is that in parent class?

}