package in.lakshay.rentACarBackend.business.requests.rentalCarRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request model for marking a car as delivered/returned
// used when customer returns the car at end of rental
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class DeliverTheCarRequest {

    // which rental to mark as delivered
    @NotNull  // required
    @Min(1)   // valid id only
    private int rentalCarId;  // pk of rental

    // which car was delivered
    @NotNull  // required
    @Min(1)   // valid id only
    private int carId;  // should match the car in rental

    // todo: should probably add delivered kilometer reading
    // and maybe condition notes (damages etc)

}
