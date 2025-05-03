package in.lakshay.rentACarBackend.business.requests.create;

// json stuff
import com.fasterxml.jackson.annotation.JsonIgnore;

// lombok makes life easier
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// auto timestamp for creation date
import org.hibernate.annotations.CreationTimestamp;

// validation - import all cuz lazy
import jakarta.validation.constraints.*;

// date handling
import java.sql.Date;
import java.time.LocalDate;

// request model for creating invoices
// pretty complex with lots of fields
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {

    // invoice number - auto-generated
    @JsonIgnore  // dont include in json
    @Pattern(regexp = "^[0-9]{16}", message = "must be 16 digits") //todo: fix error msg
    private String invoiceNo;  // unique invoice number

    // when invoice was created - auto-set
    @JsonIgnore  // dont include in json
    @CreationTimestamp  // hibernate magic
    private Date creationDate;  // when invoice was generated

    // rental period
    @NotNull  // gotta have a start date
    private LocalDate startDate;  // when rental began

    @NotNull  // gotta have an end date
    private LocalDate finishDate;  // when rental ended

    // how many days total
    @NotNull
    @Min(1)  // at least 1 day
    private short totalRentalDay;  // total days rented

    // price breakdown
    @NotNull
    @Min(0)  // cant be negative
    private double priceOfDays;  // base rental cost

    // extra fee if returned to different city
    @Min(0)  // cant be negative
    private double priceOfDiffCity;  // one-way fee if applicable

    // cost of any extras/add-ons
    @Min(0)  // cant be negative
    private double priceOfAdditionals;  // gps, child seat, etc

    // total price including everything
    @NotNull
    @Min(1)  // must cost something
    private double rentalCarTotalPrice;  // grand total

    // references to related entities
    @NotNull
    @Min(1)  // valid rental id
    private int rentalCarId;  // which rental this is for

    @NotNull
    @Min(1)  // valid customer id
    private int customerId;  // who's being billed

    @NotNull
    @Min(1)  // valid payment id
    private int paymentId;  // how it was paid

    // todo: maybe add discount field?
    // also should add tax breakdown

}
