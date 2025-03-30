package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CarMaintenanceNotFoundException extends BusinessException {

    public CarMaintenanceNotFoundException(String message) {
        super(message);
    }
}
