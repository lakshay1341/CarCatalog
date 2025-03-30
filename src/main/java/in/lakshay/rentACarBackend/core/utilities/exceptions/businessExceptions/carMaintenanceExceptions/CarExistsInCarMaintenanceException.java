package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class CarExistsInCarMaintenanceException extends BusinessException {

    public CarExistsInCarMaintenanceException(String message) {
        super(message);
    }
}
