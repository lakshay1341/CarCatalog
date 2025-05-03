package in.lakshay.rentACarBackend.business.requests.update;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation - import all cuz lazy
import jakarta.validation.constraints.*;

// request model for updating individual (person) customers
// extends customer request with person-specific fields
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest extends UpdateCustomerRequest {

    // updated first name
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Size(min = 3)  // reasonable length
    private String firstName;  // person's first/given name

    // updated last name
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Size(min = 3)  // reasonable length
    private String lastName;  // person's last/family name

    // national ID - can be updated but must be unique
    @NotNull  // cant be null
    @NotBlank  // cant be empty
    @Pattern(regexp = "^[0-9]{11}", message = "must be 11 digits") //todo: fix error msg
    private String nationalIdentity;  // govt ID number

    // todo: maybe add date of birth?
    // also should add driver's license info

}
