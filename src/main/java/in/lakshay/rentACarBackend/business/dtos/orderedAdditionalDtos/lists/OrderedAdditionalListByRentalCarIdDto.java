package in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing extras for a specific rental
// same fields as base list dto for now
// kept separate in case we need to diverge later
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalListByRentalCarIdDto {

    // basic info
    private int orderedAdditionalId;  // pk
    private short orderedAdditionalQuantity;  // how many

    // related additional
    private int additionalId;  // what type
    private String additionalName;  // display name

    // which rental - redundant since we filtered by this
    // but kept for consistency with other dtos
    private int rentalCarId;

}
