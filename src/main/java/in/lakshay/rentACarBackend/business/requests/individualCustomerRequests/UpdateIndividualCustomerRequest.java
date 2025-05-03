package in.lakshay.rentACarBackend.business.requests.individualCustomerRequests;

import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.requests.customerRequests.UpdateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

// request obj for updating individual customer info
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest extends UpdateCustomerRequest {

    // first name - min 3 chars
    @NotNull
    @NotBlank
    @Size(min = 3)  // too short names r weird
    private String firstName;

    // last name - same rules
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String lastName;

    // national id - must be exactly 11 digits
    // todo: maybe add better validation? this is kinda basic
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{11}", message = BusinessMessages.IndividualCustomerMessages.NATIONAL_IDENTITY_NOT_VALID)
    private String nationalIdentity;  // gotta be valid format or boom!

}
