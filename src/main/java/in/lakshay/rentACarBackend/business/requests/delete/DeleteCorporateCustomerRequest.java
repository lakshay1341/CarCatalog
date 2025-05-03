package in.lakshay.rentACarBackend.business.requests.delete;

import lombok.Data;

// just extends the base customer request
// nothing special needed for corporate customers
@Data
public class DeleteCorporateCustomerRequest extends DeleteCustomerRequest {
    // inherits everything from parent
    // maybe add company-specific stuff later?
}
