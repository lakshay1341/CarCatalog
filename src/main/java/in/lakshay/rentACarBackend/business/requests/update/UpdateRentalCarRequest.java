package in.lakshay.rentACarBackend.business.requests.update;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalCarRequest {

    @NotNull
    @Min(1)
    private int rentalCarId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate finishDate;

    @NotNull
    @Min(1)
    private int carId;

    @NotNull
    @Min(1)
    private int rentedCityId;

    @NotNull
    @Min(1)
    private int deliveredCityId;

//todo:bunları kapattım doğruluğunu düşün

    @JsonIgnore
    private Integer rentedKilometer;

    @JsonIgnore
    private Integer deliveredKilometer;

    @NotNull
    @Min(1)
    private int customerId;

}