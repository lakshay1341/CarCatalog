package in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request model for adding extras to a rental
// like gps, baby seats, etc
@Data  // lombok makes life easier
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalRequest {

    // how many of this extra to add
    @NotNull  // required
    @Min(1)   // at least 1 obvs
    private short orderedAdditionalQuantity;  // like 2 child seats

    // which extra type to add
    @NotNull  // required
    @Min(1)   // valid id only
    private int additionalId;  // fk to additionals table

    // which rental to add it to - set by server
    @JsonIgnore  // dont include in json
    private int rentalCarId;  // fk to rental_cars table

    // todo: maybe add notes field? could be useful
    // for special requests like "put child seat in back right"

}
