package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class MaintenanceReturnDateBeforeTodayException extends BusinessException {

    public MaintenanceReturnDateBeforeTodayException(String message) {
        super(message);
    }
}
