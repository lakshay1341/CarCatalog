package in.lakshay.rentACarBackend.business.dtos.invoiceDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for getting corporate invoice by invoice number
// similar to the one by id but uses invoice no instead
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerInvoiceByInvoiceNoDto extends GetInvoiceDto {

    private String companyName;  // business name
    private String taxNumber;    // tax id for accounting

    // todo: maybe add contact person field?

}