package in.lakshay.rentACarBackend.business.requests.update;

// json stuff
import com.fasterxml.jackson.annotation.JsonIgnore;

// lombok makes life easier
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// auto timestamp for creation date
import org.hibernate.annotations.CreationTimestamp;

// validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// date handling
import java.sql.Date;
import java.time.LocalDate;

// request model for updating invoices
// pretty complex with lots of fields
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

    // which invoice to update
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int invoiceId;  // pk in db

    // invoice number - shouldn't change but included
    @JsonIgnore  // dont include in json
    @Size(min = 16, max = 16)  // must be 16 chars
    private String invoiceNo;  // unique invoice number

    // creation date - shouldn't change
    @JsonIgnore  // dont include in json
    @CreationTimestamp  // hibernate magic
    private Date creationDate;  // when invoice was generated

    // updated rental period
    @NotNull  // required
    private LocalDate startDate;  // when rental began

    @NotNull  // required
    private LocalDate finishDate;  // when rental ended

    // updated rental duration
    @NotNull  // required
    @Min(1)   // at least 1 day
    private short totalRentalDay;  // total days rented

    // updated price breakdown
    @NotNull  // required
    @Min(0)   // cant be negative
    private double priceOfDays;  // base rental cost

    // updated one-way fee
    @Min(0)   // cant be negative
    private double priceOfDiffCity;  // fee if returned to different city

    // updated extras cost
    @Min(0)   // cant be negative
    private double priceOfAdditionals;  // gps, child seat, etc

    // updated total price
    @NotNull  // required
    @Min(1)   // must cost something
    private double rentalCarTotalPrice;  // grand total

    // references - shouldn't change but included
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int rentalCarId;  // fk to rental_cars table

    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int customerId;  // fk to customers table

    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int paymentId;  // fk to payments table

    // todo: maybe add discount field?
    // also should add tax breakdown

}
