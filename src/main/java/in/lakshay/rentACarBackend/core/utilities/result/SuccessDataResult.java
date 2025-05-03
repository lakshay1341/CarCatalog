package in.lakshay.rentACarBackend.core.utilities.result;

// convenience class for success results with data
// saves us from typing success=true all the time
public class SuccessDataResult<T> extends DataResult<T>{

	// empty result - no data, no msg
	public SuccessDataResult(){
		super(true, null);  // success but nothing to return
	}

	// just data, no msg
	public SuccessDataResult(T data) {
		super(true, data);  // success with data
	}

	// just msg, no data
	public SuccessDataResult(String message) {
		super(true, null, message);  // success with msg only
	}

	// both data and msg - most useful one
	public SuccessDataResult(T data, String message) {
		super(true, data, message);  // the works
	}

	// maybe add some factory methods later?
}
