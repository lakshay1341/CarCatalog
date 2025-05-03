package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to do something with a car that's in maintenance
// like trying to rent it or delete it from the system
public class CarExistsInCarMaintenanceException extends BusinessException {

    // basic constructor
    public CarExistsInCarMaintenanceException(String message) {
        super(message);  // pass to parent
    }

    // todo: maybe add a constructor that takes carId and maintenance details?
}
