package in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderedAdditionalRequest {

    @NotNull
    @Min(1)
    private int orderedAdditionalId;

    @NotNull
    @Min(1)
    private short orderedAdditionalQuantity;

    @NotNull
    @Min(1)
    private int additionalId;

    @NotNull
    @Min(1)
    private int rentalCarId;

}
