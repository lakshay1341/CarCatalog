package in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request model for adding extras to a rental
// similar to CreateOrderedAdditionalRequest but with
// explicit rental car id (not ignored)
@Data  // lombok is awesome
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalForExtraRequest {

    // how many of this extra to add
    @NotNull  // required
    @Min(1)   // at least 1
    private short orderedAdditionalQuantity;  // like 2 child seats

    // which extra type to add
    @NotNull  // required
    @Min(1)   // valid id only
    private int additionalId;  // fk to additionals table

    // which rental to add it to - explicit in this version
    @NotNull  // required
    @Min(1)   // valid id only
    private int rentalCarId;  // fk to rental_cars table
}
//todo: check if we need both this and CreateOrderedAdditionalRequest
// seems redundant - maybe consolidate?