package in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing all rentals with a specific extra
// useful for reports on popular extras
// same fields as base dto for now
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalListByAdditionalIdDto {

    // basic info
    private int orderedAdditionalId;  // pk
    private short orderedAdditionalQuantity;  // how many

    // which additional - redundant since we filtered by this
    // but kept for consistency
    private int additionalId;
    private String additionalName;

    // which rental has this extra
    private int rentalCarId;  // fk to rental

    // might add rental details later if needed

}
