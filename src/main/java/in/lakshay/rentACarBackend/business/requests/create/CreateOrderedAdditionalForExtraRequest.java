package in.lakshay.rentACarBackend.business.requests.create;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request model for adding extras to a rental
// like GPS, child seats, etc
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalForExtraRequest {

    // how many of this extra to add
    @NotNull  // cant be null
    @Min(1)  // at least 1
    private short orderedAdditionalQuantity;  // like 2 child seats

    // which extra to add
    @NotNull  // cant be null
    @Min(1)  // valid id
    private int additionalId;  // fk to additionals table

    // which rental to add it to
    @NotNull  // cant be null
    @Min(1)  // valid id
    private int rentalCarId;  // fk to rental_cars table
}
//todo: delete if not used anywhere