package in.lakshay.rentACarBackend.business.requests.corporateCustomerRequests;

import in.lakshay.rentACarBackend.business.requests.customerRequests.DeleteCustomerRequest;
import lombok.Data;

// request model for deleting corporate customers
// just extends base delete request - no additional fields needed
@Data // lombok
public class DeleteCorporateCustomerRequest extends DeleteCustomerRequest { // inherits userId
    // nothing to add here - just uses the userId from parent class
    // might need to add more fields later if we need special delete behavior

    // TODO: maybe add a reason field for tracking why customers leave?
}
