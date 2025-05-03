package in.lakshay.rentACarBackend.business.requests.corporateCustomerRequests;

import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.requests.customerRequests.CreateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation stuff
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// request model for creating new corporate customers
@Data // lombok
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest extends CreateCustomerRequest { // inherits user/customer fields

    // company name - required, 3-300 chars
    @NotNull  // can't be null
    @NotBlank // can't be empty
    @Size(min = 3, max = 300) // reasonable length
    private String companyName; // legal name of company

    // tax number - must be exactly 10 digits
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}", message = BusinessMessages.CorporateCustomerMessages.TAX_NUMBER_NOT_VALID) // validation msg
    private String taxNumber; // tax id - unique per company

}
