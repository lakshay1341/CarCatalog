package in.lakshay.rentACarBackend.core.utilities.result;

// for when things go right!
public class SuccessResult extends Result {

	// basic constructor - no frills
	public SuccessResult() {
		super(true); // always true obvs
	}

	// constructor with msg
	public SuccessResult(String message) {
		super(true, message); // success=true + msg
	}

	// todo: maybe add some helper methods?
	// like created(), updated(), etc
}