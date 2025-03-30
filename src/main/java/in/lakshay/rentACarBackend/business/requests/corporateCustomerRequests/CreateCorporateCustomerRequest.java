package in.lakshay.rentACarBackend.business.requests.corporateCustomerRequests;

import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.requests.customerRequests.CreateCustomerRequest;
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
public class CreateCorporateCustomerRequest extends CreateCustomerRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 300)
    private String companyName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}", message = BusinessMessages.CorporateCustomerMessages.TAX_NUMBER_NOT_VALID)
    private String taxNumber;

}
