package in.lakshay.rentACarBackend.business.requests.customerRequests;

import in.lakshay.rentACarBackend.business.requests.userRequests.DeleteUserRequest;
import lombok.Data;

// another empty extension class
// hmm, seems like we have duplicate classes? should check this later
@Data
public class DeleteCustomerRequest extends DeleteUserRequest {
    // nothing to see here folks
    // just passing thru the userId from parent
}
