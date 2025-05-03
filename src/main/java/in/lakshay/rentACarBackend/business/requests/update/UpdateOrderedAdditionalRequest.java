package in.lakshay.rentACarBackend.business.requests.update;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request model for updating extras on a rental
// like changing quantity of GPS, child seats, etc
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderedAdditionalRequest {

    // which ordered extra to update
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int orderedAdditionalId;  // pk in db

    // updated quantity
    @NotNull  // required
    @Min(1)   // at least 1
    private short orderedAdditionalQuantity;  // how many now

    // which extra type - shouldn't change but included
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int additionalId;  // fk to additionals table

    // which rental - shouldn't change but included
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int rentalCarId;  // fk to rental_cars table

    // todo: maybe add reason for change?
    // could be useful for audit purposes

}
