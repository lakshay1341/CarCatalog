package in.lakshay.rentACarBackend.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request class for ordered additionals when creating a rental car
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalForRentalCarRequest {

    @NotNull
    @Min(1)  // gotta be at least 1, obvs
    private short orderedAdditionalQuantity;

    @NotNull
    @Min(1)
    private int additionalId;  // references the additional service id

}
