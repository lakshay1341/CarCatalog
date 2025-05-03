package in.lakshay.rentACarBackend.business.requests.create;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation - import all cuz lazy
import jakarta.validation.constraints.*;

// request model for creating individual (person) customers
// extends customer request with person-specific fields
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest extends CreateCustomerRequest {

    // first name with validation
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Size(min = 3)  // reasonable length
    private String firstName;  // person's first/given name

    // last name with validation
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Size(min = 3)  // reasonable length
    private String lastName;  // person's last/family name

    // national ID number - must be 11 digits
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Pattern(regexp = "^[0-9]{11}", message = "must be 11 digits") //todo: fix error msg
    private String nationalIdentity;  // govt ID number

    // todo: maybe add date of birth?
    // also should add driver's license info

}
