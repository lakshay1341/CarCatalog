package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;

public class ModelYearAfterThisYearException extends BusinessException {

    public ModelYearAfterThisYearException(String message) {
        super(message);
    }
}
