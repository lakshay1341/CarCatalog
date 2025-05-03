package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

// thrown when someone tries to set a maintenance return date in the past
// we can't go back in time... yet! :)
public class MaintenanceReturnDateBeforeTodayException extends BusinessException {

    // basic constructor
    public MaintenanceReturnDateBeforeTodayException(String message) {
        super(message);  // pass to parent
    }

    // todo: maybe add a constructor that takes the invalid date?
    // would make error msgs more consistent
}
