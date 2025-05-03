package in.lakshay.rentACarBackend.business.requests.corporateCustomerRequests;

import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.requests.customerRequests.UpdateCustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation imports
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// request model for updating corporate customers
// very similar to create request but extends update base class
@Data // lombok
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest extends UpdateCustomerRequest { // has userId from parent

    // company name - same validation as create
    @NotNull
    @NotBlank
    @Size(min = 3, max = 300) // min/max length
    private String companyName; // can be updated

    // tax number - same validation as create
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}", message = BusinessMessages.CorporateCustomerMessages.TAX_NUMBER_NOT_VALID) // must be 10 digits
    private String taxNumber; // can be updated but must be unique

}
