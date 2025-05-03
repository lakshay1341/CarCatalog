package in.lakshay.rentACarBackend.core.utilities.result;

// wrapper for error responses that include data
// used when operation fails but we still want to return something
public class ErrorDataResult<T> extends DataResult<T> {

	// default constructor - no data, no message
	public ErrorDataResult() {
		super(false, null);  // always false for errors
	}

	// just data, no message
	public ErrorDataResult(T data) {
		super(false, data);  // success=false
	}

	// just message, no data
	public ErrorDataResult(String message) {
		super(false, null, message);  // null data
	}

	// both data and message - most useful constructor
	public ErrorDataResult(T data, String message) {
		super(false, data, message);  // the works
	}

	// todo: maybe add a static factory method?
	// would make it cleaner to create these

}
