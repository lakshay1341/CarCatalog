package in.lakshay.rentACarBackend.core.utilities.result;

// base class for all our api responses
// keeps things consistent across the app
// this is like our standard envelope format
public class Result {

	// did the operation succeed or fail
	private boolean success;

	// msg to explain what happened to the client
	private String message;

	// simple constructor - just success flag
	public Result(boolean success) {
		this.success = success; // just set the flag
	}

	// constructor with msg - more useful for clients
	public Result(boolean success, String message) {
		this(success); // call the other constructor first
		this.message = message; // then set msg
	}

	// getters - no setters cuz we want immutable responses
	// don't want anyone changing the result after it's created!

	// did it work? true=success, false=failure
	public boolean isSuccess() {
		return this.success; // true=yay, false=nope
	}

	// what happened? success or error msg
	public String getMessage() {
		return this.message; // might be null if not set
	}

	// TODO: maybe add timestamp field? would be useful for debugging
	// TODO: add request ID for tracking?
}
