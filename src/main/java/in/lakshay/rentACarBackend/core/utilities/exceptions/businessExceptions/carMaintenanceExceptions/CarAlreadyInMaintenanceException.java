package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when trying to put a car in maintenance that's already in maintenance
// can't maintain a car twice at the same time, duh!
public class CarAlreadyInMaintenanceException extends BusinessException {

    // basic constructor
    public CarAlreadyInMaintenanceException(String message) {
        super(message);  // pass to parent
    }

    // might add more specific handling later
    // like showing when the car will be out of maintenance
}
