package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when we cant find a maintenance record
// usually means someone tried to access with wrong ID or record was deleted
public class CarMaintenanceNotFoundException extends BusinessException {

    // basic constructor
    public CarMaintenanceNotFoundException(String message) {
        super(message);  // pass to parent
    }

    // might add more specific handling later
    // like showing the maintenance ID that was requested
}
