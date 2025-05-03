package in.lakshay.rentACarBackend.business.dtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// invoice dto - for returning invoice details
// simpler version than the one in invoice package
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceDto {

    // invoice identifiers
    private int invoiceId;      // pk in db
    private String invoiceNo;   // human readable number
    private LocalDate creationDate;  // when created

    // rental info
    private int rentalCarId;    // which rental
    private LocalDate startDate;    // rental start
    private LocalDate finishDate;   // rental end
    private short totalRentalDay;   // total days

    // financial
    private double rentalCarTotalPrice;  // total amount

    // customer contact
    private String email;    // for sending invoice

    //todo: need to add first/last name here
    // should probably add payment status too
}
