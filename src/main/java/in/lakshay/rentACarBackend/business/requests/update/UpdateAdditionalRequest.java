package in.lakshay.rentACarBackend.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalRequest {

    @NotNull
    @Min(1)
    private int additionalId;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 300)
    private String additionalName;

    @NotNull
    @Min(1)
    private double additionalDailyPrice;

    @NotNull
    @Min(1)
    private short maxUnitsPerRental;

}
