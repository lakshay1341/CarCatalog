package in.lakshay.rentACarBackend.business.dtos.invoiceDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

// invoice dto - for returning invoice details
// lots of fields here - maybe split into sections?
@Data  // lombok makes life easier!
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceDto {

    // basic info
    private int invoiceId;      // pk
    private String invoiceNo;   // human readable number
    private Date creationDate;  // when created

    // rental info
    private int rentalCarId;    // which rental
    private LocalDate startDate;    // when rented
    private LocalDate finishDate;   // when returned
    private short totalRentalDay;   // how many days

    // price breakdown stuff
    private double priceOfDays;     // base price
    private double priceOfDiffCity;  // extra for diff city return
    private double priceOfAdditionals;  // extras like gps etc
    private double rentalCarTotalPrice;  // total

    // customer info
    private int customerId;  // who rented
    private String email;    // contact

    // payment
    private int paymentId;   // link to payment

    // todo: maybe add formatted price getters?
    // also should probably add payment status somewhere
}
