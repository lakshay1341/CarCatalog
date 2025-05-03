package in.lakshay.rentACarBackend.business.requests.individualCustomerRequests;

import in.lakshay.rentACarBackend.business.requests.customerRequests.DeleteCustomerRequest;
import lombok.Data;

// just extends the base customer delete request
// nothing special needed for individual customers
@Data // lombok makes life easier
public class DeleteIndividualCustomerRequest extends DeleteCustomerRequest {
    // inherits everything from parent
    // might add specific fields later if needed

    // todo: maybe add reason for deletion?
}
