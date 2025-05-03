package in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions;

// base exception for all business logic errors
// todo: maybe add more specific error handling later?
public class BusinessException extends Exception{

	// basic constructor - just takes a msg
	public BusinessException(String message) {
		super(message);
	}

}
