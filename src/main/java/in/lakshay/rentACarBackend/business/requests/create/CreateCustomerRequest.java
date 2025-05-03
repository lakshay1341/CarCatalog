package in.lakshay.rentACarBackend.business.requests.create;

// json stuff
import com.fasterxml.jackson.annotation.JsonIgnore;

// lombok makes life easier
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// auto timestamp for registration
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

// base request for creating customers
// extends user request cuz customers are users
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest extends CreateUserRequest {

    // auto-set on creation - dont need client to set this
    @JsonIgnore  // dont include in json
    @CreationTimestamp  // hibernate magic
    private LocalDate registrationDate;  // when customer signed up

    // hmm should prob add more fields here
    // like maybe phone number or address?

}
