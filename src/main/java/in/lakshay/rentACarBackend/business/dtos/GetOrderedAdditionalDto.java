package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for ordered extras on a rental
// like when someone adds GPS or baby seats to their rental
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderedAdditionalDto {

    private int orderedAdditionalId;  // pk

    // how many of this item they ordered
    // like 2 baby seats or whatever
    private short orderedAdditionalQuantity;

    // what type of extra is this
    private int additionalId;  // fk to additionals table
    private String additionalName;  // display name

    // which rental this belongs to
    private int rentalCarId;  // fk to rental_cars table

    // todo: maybe add price calc here?
    // would be nice to show subtotal

}
