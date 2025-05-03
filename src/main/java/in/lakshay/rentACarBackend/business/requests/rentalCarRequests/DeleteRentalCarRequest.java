package in.lakshay.rentACarBackend.business.requests.rentalCarRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request model for deleting a rental
// super simple - just need the id
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRentalCarRequest {

    // which rental to delete
    @NotNull  // required
    @Min(1)   // valid id only
    private int rentalCarId;  // pk

    // todo: maybe add a safety check?
    // should verify rental is completed before deleting

}
