package in.lakshay.rentACarBackend.business.requests.individualCustomerRequests;

import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.requests.customerRequests.UpdateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest extends UpdateCustomerRequest {

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String lastName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{11}", message = BusinessMessages.IndividualCustomerMessages.NATIONAL_IDENTITY_NOT_VALID)
    private String nationalIdentity;

}
