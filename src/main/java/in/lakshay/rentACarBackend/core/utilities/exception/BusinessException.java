package in.lakshay.rentACarBackend.core.utilities.exception;

// base exception class for all business logic exceptions
// all domain-specific exceptions inherit from this
public class BusinessException extends Exception {

	// simple constructor - just passes msg to Exception parent
	public BusinessException(String name) {
		super(name);  // let parent handle the details
	}

	// todo: maybe add more constructors?
	// like one that takes a cause exception
	// or one with both message and cause

	// also should we make this abstract? hmm
}

