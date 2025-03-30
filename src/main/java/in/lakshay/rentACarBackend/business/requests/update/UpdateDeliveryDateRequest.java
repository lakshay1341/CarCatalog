package in.lakshay.rentACarBackend.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NotNull
public class UpdateDeliveryDateRequest {

    @NotNull
    @Min(1)
    private int rentalCarId;

    @NotNull
    private LocalDate finishDate;

}
