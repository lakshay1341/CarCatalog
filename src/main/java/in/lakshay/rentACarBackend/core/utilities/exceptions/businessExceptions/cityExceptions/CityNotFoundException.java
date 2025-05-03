package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when we cant find a city in the database
// usually means someone tried to access with wrong ID
public class CityNotFoundException extends BusinessException {

    // basic constructor
    public CityNotFoundException(String message) {
        super(message);  // pass to parent
    }

    // might add more specific handling later
    // like showing the city ID that was requested
}
