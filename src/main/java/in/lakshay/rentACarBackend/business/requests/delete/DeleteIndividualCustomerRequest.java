package in.lakshay.rentACarBackend.business.requests.delete;

import lombok.Data;

// extends the base customer request
// nothing special needed for individual customers
@Data
public class DeleteIndividualCustomerRequest extends DeleteCustomerRequest {
    // empty for now - just need the type for controller
    // todo: maybe add reason for deletion?
}
