package in.lakshay.rentACarBackend.core.utilities.result;

// result class that has actual data in it
public class DataResult<T> extends Result {

	private T data;  // the stuff we're returning

	// basic constructor
	public DataResult(boolean success, T data){
		super(success);
		this.data = data;  // store the data
	}

	// constructor with msg
	public DataResult(boolean success, T data, String message) {
		super(success, message); // parent stuff first
		this.data = data; // then our stuff
	}

	// getter
	public T getData() {
		return this.data; // give em the data
	}

	// todo: maybe add some helper methods?
	// like map() or filter() for collections?
}
