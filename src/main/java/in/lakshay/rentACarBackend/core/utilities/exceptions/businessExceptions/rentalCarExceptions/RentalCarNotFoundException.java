package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions;

// base exception
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when rental record not found
// usually means invalid rental id or deleted rental
public class RentalCarNotFoundException extends BusinessException {

    // nothing fancy here
    public RentalCarNotFoundException(String message) {
        super(message);  // let parent do the work
    }

    // todo: add more specific error handling?
    // like showing which car or customer was involved
}
