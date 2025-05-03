package in.lakshay.rentACarBackend.business.requests.delete;

import lombok.Data;

// just extends DeleteUserRequest cuz customers r users
// nothing to add here, just need the type for api
@Data
public class DeleteCustomerRequest extends DeleteUserRequest {
    // empty - inherits userId from parent
    // might add customer-specific stuff later if needed
}
