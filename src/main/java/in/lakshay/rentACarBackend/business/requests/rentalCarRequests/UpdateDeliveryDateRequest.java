package in.lakshay.rentACarBackend.business.requests.rentalCarRequests;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// request model for extending/changing a rental end date
// used when customer wants to keep car longer/shorter
@Data  // lombok ftw
@AllArgsConstructor
@NotNull  // hmm, this should be NoArgsConstructor? weird annotation on class
public class UpdateDeliveryDateRequest {

    // which rental to update
    @NotNull  // required
    @Min(1)   // valid id only
    private int rentalCarId;  // pk of rental

    // new end date for the rental
    @NotNull  // required
    private LocalDate finishDate;  // when they'll return it now

    // todo: should probably validate that new date is after start date
    // and maybe add a reason for the change

}
