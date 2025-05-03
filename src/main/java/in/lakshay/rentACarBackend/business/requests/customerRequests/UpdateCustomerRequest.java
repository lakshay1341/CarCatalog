package in.lakshay.rentACarBackend.business.requests.customerRequests;

import in.lakshay.rentACarBackend.business.requests.userRequests.UpdateUserRequest;
import lombok.Data;

// yet another empty extension class
// just inherits everything from UpdateUserRequest
// might add customer-specific fields later if needed
@Data
public class UpdateCustomerRequest extends UpdateUserRequest {
    // nothing to see here
    // just passing thru the user fields
}
