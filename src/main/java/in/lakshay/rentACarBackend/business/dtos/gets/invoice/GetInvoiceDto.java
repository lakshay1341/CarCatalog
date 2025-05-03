package in.lakshay.rentACarBackend.business.dtos.gets.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

// base invoice dto - has all the common fields
// extended by specific customer type invoice dtos
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceDto {

    // invoice identifiers
    private int invoiceId;      // pk in db
    private String invoiceNo;   // human readable invoice number
    private Date creationDate;  // when invoice was created

    // rental details
    private int rentalCarId;    // which rental this is for
    private LocalDate startDate;    // when rental started
    private LocalDate finishDate;   // when it ended/will end
    private short totalRentalDay;   // how many days total

    // pricing breakdown - good for itemized receipts
    private double priceOfDays;         // base daily rate * days
    private double priceOfDiffCity;     // fee for diff return location
    private double priceOfAdditionals;  // extras like gps, child seat etc
    private double rentalCarTotalPrice; // grand total

    // customer info
    private int customerId;  // fk to customer
    private String email;    // for sending invoice

    // payment tracking
    private int paymentId;   // link to payment record

    // todo: add payment status? paid/unpaid/partial?

}
