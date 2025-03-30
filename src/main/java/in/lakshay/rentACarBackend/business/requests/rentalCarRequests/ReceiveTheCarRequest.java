package in.lakshay.rentACarBackend.business.requests.rentalCarRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveTheCarRequest {

    @NotNull
    @Min(1)
    private int rentalCarId;

    @NotNull
    @Min(1)
    private int carId;

    @NotNull
    @Min(1)
    private int deliveredKilometer;

}
