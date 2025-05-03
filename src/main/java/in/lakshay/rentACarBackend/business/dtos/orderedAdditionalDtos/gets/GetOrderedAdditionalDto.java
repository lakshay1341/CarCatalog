package in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for getting a single ordered additional
// represents an extra service added to a rental
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderedAdditionalDto {

    // basic info
    private int orderedAdditionalId;  // pk
    private short orderedAdditionalQuantity;  // how many units

    // related additional service
    private int additionalId;  // fk to additional
    private String additionalName;  // name for display

    // related rental
    private int rentalCarId;  // fk to rental

    // todo: maybe add price calculation?

}
