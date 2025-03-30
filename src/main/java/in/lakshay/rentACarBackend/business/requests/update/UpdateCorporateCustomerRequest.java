package in.lakshay.rentACarBackend.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest extends UpdateCustomerRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 300)
    private String companyName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}", message = "not number") //todo:açıklama düzelt
    private String taxNumber;

}
