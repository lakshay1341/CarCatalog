package in.lakshay.rentACarBackend.business.requests.invoiceRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages; // typo in package name lol
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;

// request object for creating a new invoice
// has validation rules for all fields
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {

    // these are auto-generated so we ignore them in JSON
    @JsonIgnore
    @Pattern(regexp = "^[0-9]{16}", message = BusinessMessages.InvoiceMessages.INVOICE_NO_NOT_VALID)
    private String invoiceNo;  // 16 digit number

    @JsonIgnore
    @CreationTimestamp  // auto-set to current time
    private Date creationDate;  // when invoice is created

    // rental period
    @NotNull
    private LocalDate startDate;  // when rental started

    @NotNull
    private LocalDate finishDate;  // when rental ended

    @NotNull
    @Min(1)  // must rent for at least 1 day
    private short totalRentalDay;  // total days rented

    // price breakdown - all must be positive
    @NotNull
    @Min(0)  // can't be negative
    private double priceOfDays;  // base price

    @Min(0)  // optional fee
    private double priceOfDiffCity;  // fee for diff return location

    @Min(0)  // optional extras
    private double priceOfAdditionals;  // extras like gps etc

    @NotNull
    @Min(1)  // total must be at least something
    private double rentalCarTotalPrice;  // total amount

    // foreign keys - all required
    @NotNull
    @Min(1)  // must be valid id
    private int rentalCarId;  // which rental

    @NotNull
    @Min(1)  // must be valid id
    private int customerId;  // who rented

    @NotNull
    @Min(1)  // must be valid id
    private int paymentId;  // payment record

    // todo: maybe add validation to ensure total = sum of parts?
    // also should check that finish date > start date

}
