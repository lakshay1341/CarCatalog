package in.lakshay.rentACarBackend.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request for deleting invoices
// should be used carefully - might need audit trail
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteInvoiceRequest {

    // invoice to delete
    @NotNull // required
    @Min(1)  // valid id
    private int invoiceId;

    // hmm should we add a reason field?
    // prob good for accounting purposes

}
