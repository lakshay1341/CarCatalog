package in.lakshay.rentACarBackend.business.requests.customerRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.lakshay.rentACarBackend.business.requests.userRequests.CreateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

// just a simple extension of user request with registration date
// nothing fancy here
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest extends CreateUserRequest {

    // auto-generated timestamp - dont need to set this manually
    // system handles it for us
    @JsonIgnore  // hide from json
    @CreationTimestamp
    private LocalDate registrationDate;  // when customer signed up

}
