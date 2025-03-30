package in.lakshay.rentACarBackend.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalCarRequest {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate finishDate;

    @NotNull
    @Min(1)
    private int carId;

    @NotNull
    @Min(1)
    private int rentedCityCityId;

    @NotNull
    @Min(1)
    private int deliveredCityId;

    @NotNull
    @Min(1)
    private int customerId;

}