package in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request model for updating extras on a rental
// like changing quantity of child seats, etc
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderedAdditionalRequest {

    // which ordered extra to update
    @NotNull  // required
    @Min(1)   // valid id only
    private int orderedAdditionalId;  // pk

    // new quantity
    @NotNull  // required
    @Min(1)   // at least 1
    private short orderedAdditionalQuantity;  // how many now

    // which extra type - shouldn't change but included
    @NotNull  // required
    @Min(1)   // valid id only
    private int additionalId;  // fk to additionals

    // which rental - shouldn't change but included
    @NotNull  // required
    @Min(1)   // valid id only
    private int rentalCarId;  // fk to rental_cars

    // hmm, should we allow changing the additional type?
    // prob better to delete and create new one instead

}
