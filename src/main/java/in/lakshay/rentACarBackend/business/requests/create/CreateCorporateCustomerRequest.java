package in.lakshay.rentACarBackend.business.requests.create;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// request model for creating corporate (business) customers
// extends customer request with business-specific fields
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest extends CreateCustomerRequest {

    // company name with validation
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Size(min = 3, max = 300)  // reasonable length
    private String companyName;  // legal business name

    // tax ID number - must be 10 digits
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Pattern(regexp = "^[0-9]{10}", message = "must be 10 digits") //todo: fix error msg
    private String taxNumber;  // govt tax ID

    // todo: maybe add company address?
    // also should add contact person info

}
