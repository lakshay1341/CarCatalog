package in.lakshay.rentACarBackend.business.requests.rentalCarRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request model for receiving a car from customer
// used when customer returns the car at end of rental
// similar to DeliverTheCarRequest but includes odometer reading
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveTheCarRequest {

    // which rental is being completed
    @NotNull  // required
    @Min(1)   // valid id only
    private int rentalCarId;  // pk of rental

    // which car is being returned
    @NotNull  // required
    @Min(1)   // valid id only
    private int carId;  // should match the car in rental

    // final odometer reading
    @NotNull  // required
    @Min(1)   // must be positive
    private int deliveredKilometer;  // how many km on the clock now

    // todo: maybe add condition notes field?
    // would be useful for damage reporting
    // also should validate km is > than when rented

}
