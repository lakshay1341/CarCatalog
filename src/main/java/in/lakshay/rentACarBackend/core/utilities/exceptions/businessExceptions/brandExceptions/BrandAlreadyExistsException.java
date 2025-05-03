package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.brandExceptions;

// thrown when trying to create a brand that already exists
// brand names must be unique in the system
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class BrandAlreadyExistsException extends BusinessException {

    // basic constructor - just passes msg to parent
    public BrandAlreadyExistsException(String message) {
        super(message);  // let parent handle it
    }

    // todo: maybe add a constructor that takes the brand name?
    // would make error msgs more consistent
    // public BrandAlreadyExistsException(String brandName) {
    //    super("Brand already exists: " + brandName);
    // }
}
