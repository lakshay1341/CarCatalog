package in.lakshay.rentACarBackend.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request for cancelling a rental
// be careful with this one - might need special handling
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRentalCarRequest {

    // which rental to cancel
    @NotNull // required
    @Min(1)  // valid id
    private int rentalCarId;

    // todo: add cancellation reason?
    // might be useful for reporting

}
