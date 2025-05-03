package in.lakshay.rentACarBackend.business.requests.update;

// lombok - no need for NoArgsConstructor here
import lombok.AllArgsConstructor;
import lombok.Data;

// validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// date handling
import java.time.LocalDate;

// specialized request for just updating the return date
// used when customer wants to extend/shorten rental
@Data  // lombok ftw
@AllArgsConstructor
@NotNull  // hmm this should be on the class? weird
public class UpdateDeliveryDateRequest {

    // which rental to update
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int rentalCarId;  // pk in db

    // new return date
    @NotNull  // required
    private LocalDate finishDate;  // when car will be returned

    // todo: maybe add reason field?
    // like "customer requested extension" etc

}
