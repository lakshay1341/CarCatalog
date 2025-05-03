package in.lakshay.rentACarBackend.business.requests.update;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// request model for updating corporate (business) customers
// extends customer request with business-specific fields
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest extends UpdateCustomerRequest {

    // updated company name
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Size(min = 3, max = 300)  // reasonable length
    private String companyName;  // legal business name

    // updated tax ID - can be changed but must be unique
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Pattern(regexp = "^[0-9]{10}", message = "must be 10 digits") //todo: fix error msg
    private String taxNumber;  // govt tax ID

    // todo: maybe add company address?
    // also should add contact person info

}
