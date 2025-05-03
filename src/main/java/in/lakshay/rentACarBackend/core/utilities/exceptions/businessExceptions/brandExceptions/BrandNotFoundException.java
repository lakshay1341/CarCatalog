package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when brand not found in db - usually means bad id
// happens when someone tries to access a brand that doesn't exist
public class BrandNotFoundException extends BusinessException {

    // just pass msg to parent class - nothing fancy here
    public BrandNotFoundException(String message) {
        super(message);  // this will be shown to user in the response
    }

    // todo: maybe add a constructor that takes brandId?
    // would make error msgs more consistent
}
