package in.lakshay.rentACarBackend.business.requests.invoiceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request for deleting an invoice
// just needs the id - super simple
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteInvoiceRequest {

    @NotNull  // can't be null
    @Min(1)   // must be positive
    private int invoiceId;  // pk of invoice to delete

    // should probably add some kind of confirmation field?
    // deleting invoices is kinda serious

}
