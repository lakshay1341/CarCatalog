package in.lakshay.rentACarBackend.core.utilities.result;

// simple wrapper for errors
// makes the code cleaner
public class ErrorResult extends Result{

	// basic constructor - no msg
	public ErrorResult() {
		super(false); // always false cuz it's an error duh
		// maybe should log something here? meh, too noisy
	}

	// constructor w/ error message
	public ErrorResult(String message) {
		super(false, message); // just pass to parent
	}

	// todo: add helper methods for common errors?
	// like notFound(), alreadyExists(), etc
	// would make code cleaner

	// maybe add severity levels later? low/med/high
	// or add error codes? dunno if worth it
}