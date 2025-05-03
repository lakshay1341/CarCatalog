package in.lakshay.rentACarBackend.business.requests.invoiceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

// request for getting invoices between two dates
// used for reports and date range filtering
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceGetDateBetweenRequest {

    @NotNull  // must provide start date
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // format for parsing
    private Date startDate;  // beginning of range

    @NotNull  // must provide end date
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // same format
    private Date endDate;  // end of range

    // todo: add validation to ensure endDate >= startDate
    // would be nice to have custom validator for this

}
