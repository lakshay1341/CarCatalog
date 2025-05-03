package in.lakshay.rentACarBackend.business.dtos.invoiceDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

// dto for listing invoices - used in search results and tables
// has all the basic invoice fields needed for display
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListDto {

    // basic invoice info
    private int invoiceId;  // pk
    private String invoiceNo;  // human readable invoice number
    private Date creationDate;  // when invoice was created

    // rental info
    private int rentalCarId;  // fk to rental
    private LocalDate startDate;  // when rental started
    private LocalDate finishDate;  // when rental ended
    private short totalRentalDay;  // how many days rented

    // price breakdown
    private double priceOfDays;  // base price
    private double priceOfDiffCity;  // fee for diff return location
    private double priceOfAdditionals;  // extras like gps etc
    private double rentalCarTotalPrice;  // total amount

    // customer info
    private int customerId;  // fk to customer
    private String email;  // customer email

    // payment tracking
    private int paymentId;  // fk to payment

    //todo: add customer name fields - would be nice for display
    // also maybe add payment status?

}
