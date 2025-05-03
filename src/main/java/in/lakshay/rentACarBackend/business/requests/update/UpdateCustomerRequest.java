package in.lakshay.rentACarBackend.business.requests.update;

// just need @Data from lombok
import lombok.Data;

// base request for updating customers
// extends user request - just a marker class for now
@Data  // lombok ftw
public class UpdateCustomerRequest extends UpdateUserRequest {
    // no additional fields yet
    // just a marker class to distinguish customer updates

    // todo: maybe add customer-specific fields later?
    // like contact info or something
}
