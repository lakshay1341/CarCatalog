package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CarAlreadyInMaintenanceException extends BusinessException {

    public CarAlreadyInMaintenanceException(String message) {
        super(message);
    }
}
