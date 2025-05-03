package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions;

// thrown when trying to delete a brand that's still used by cars
// need to remove/reassign cars first before deleting brand
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class BrandExistsInCarException extends BusinessException {

    // simple constructor - just passes msg to parent
    public BrandExistsInCarException(String message) {
        super(message);  // parent handles the details
    }

    // hmm maybe should add brandId param to make error msgs more consistent?
    // or maybe a static factory method? idk
}
