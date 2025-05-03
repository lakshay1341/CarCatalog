package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to add a city that already exists
// city names must be unique in our system
public class CityAlreadyExistsException extends BusinessException {

    // basic constructor
    public CityAlreadyExistsException(String message) {
        super(message);  // pass to parent
    }

    // might add a constructor that takes city name later
    // would make error msgs more consistent
}
