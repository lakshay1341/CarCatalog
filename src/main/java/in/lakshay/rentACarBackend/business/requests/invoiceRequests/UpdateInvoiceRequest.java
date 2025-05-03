package in.lakshay.rentACarBackend.business.requests.invoiceRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

// request for updating invoice details
// pretty complex with lots of fields
@Data // lombok magic
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

    // which invoice to update
    @NotNull
    @Min(1)
    private int invoiceId;

    // system-generated, not user input
    @JsonIgnore // don't expose in API
    @Size(min = 16, max = 16) // fixed format
    private String invoiceNo;

    // creation timestamp handled by system
    @JsonIgnore // don't expose in API
    @CreationTimestamp
    private Date creationDate;

    // rental period start
    @NotNull // required
    private LocalDate startDate;

    // rental period end
    @NotNull // required
    private LocalDate finishDate;

    // total days of rental
    @NotNull
    @Min(1) // at least 1 day
    private short totalRentalDay;

    // cost for the rental days
    @NotNull
    @Min(0) // can't be negative
    private double priceOfDays;

    // extra fee if returned to diff city
    @Min(0) // can be 0 if same city
    private double priceOfDiffCity;

    // cost of any extras/add-ons
    @Min(0) // can be 0 if no extras
    private double priceOfAdditionals;

    // total price for the rental
    @NotNull
    @Min(1) // gotta charge something!
    private double rentalCarTotalPrice; // sum of all costs

    // which rental this invoice is for
    @NotNull
    @Min(1)
    private int rentalCarId;

    // who's paying
    @NotNull
    @Min(1)
    private int customerId;

    // link to payment record
    @NotNull
    @Min(1)
    private int paymentId;

    // todo: maybe add discount field?
    // could be useful for promos

}
