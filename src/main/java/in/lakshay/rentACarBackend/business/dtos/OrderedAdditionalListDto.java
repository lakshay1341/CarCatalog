package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing ordered extras/add-ons
// used when showing what extras were added to a rental
@Data  // lombok is awesome
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalListDto {

    private int orderedAdditionalId;  // pk
    private short orderedAdditionalQuantity;  // how many of this item

    // the actual extra/add-on
    private int additionalId;     // fk to additional
    private String additionalName;  // name like "GPS" or "Child Seat"

    // which rental this belongs to
    private int rentalCarId;  // fk to rental

    // todo: maybe add price? would be nice to show in UI
    // also should prob add a getTotal() helper

}
