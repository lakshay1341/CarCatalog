package in.lakshay.rentACarBackend.business.dtos.gets.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// invoice dto specifically for corporate customers
// extends the base invoice with company-specific fields
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerInvoiceDto extends GetInvoiceDto {

    private String companyName;  // business name for the invoice
    private String taxNumber;    // tax id - needed for accounting

    // todo: maybe add department code? some companies need it

}