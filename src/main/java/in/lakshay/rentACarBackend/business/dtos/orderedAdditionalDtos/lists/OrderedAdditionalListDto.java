package in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing ordered additionals
// used when showing all extras across rentals
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalListDto {

    // basic info
    private int orderedAdditionalId;  // pk
    private short orderedAdditionalQuantity;  // how many

    // related additional
    private int additionalId;  // what type of extra
    private String additionalName;  // name for display

    // related rental
    private int rentalCarId;  // which rental

    // same fields as get dto for now
    // might diverge in future if we add more fields

}
