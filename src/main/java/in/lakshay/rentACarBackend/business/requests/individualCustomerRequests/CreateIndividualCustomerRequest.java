package in.lakshay.rentACarBackend.business.requests.individualCustomerRequests;

import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.requests.customerRequests.CreateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;

// request for creating individual (non-corporate) customers
// extends base customer request with personal info
@Data // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest extends CreateCustomerRequest {

    // first name with validation
    @NotNull // required
    @NotBlank // can't be empty
    @Size(min = 3) // reasonable length
    private String firstName; // like "John"

    // last name with validation
    @NotNull // required
    @NotBlank // can't be empty
    @Size(min = 3) // reasonable length
    private String lastName; // like "Smith"

    // national ID number - format depends on country
    @NotNull // required
    @NotBlank // can't be empty
    @Pattern(regexp = "^[0-9]{11}", message = BusinessMessages.IndividualCustomerMessages.NATIONAL_IDENTITY_NOT_VALID)
    private String nationalIdentity; // must be 11 digits

    // todo: maybe add date of birth?
    // would be useful for age verification

}
