package in.lakshay.rentACarBackend.core.utilities.exceptions;

// exception imports
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;
import in.lakshay.rentACarBackend.core.utilities.result.ErrorDataResult;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// java stuff
import java.util.HashMap;
import java.util.Map;

// global exception handler for the whole app
// catches all exceptions and returns nice error responses
// instead of ugly stack traces
@RestControllerAdvice  // applies to all controllers
public class GlobalExceptionHandler {
    // this class is getting kinda big... might need to split it up later


    // handles validation errors from @Valid annotations
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)  // 400 error
    public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException){
        // collect all validation errors
        Map<String, String> validationErrors = new HashMap<String, String>();  // field -> error msg

        // loop thru all field errors
        for(FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());  // add to map
        }

        // wrap in our standard error format
        ErrorDataResult<Object> error = new ErrorDataResult<>(validationErrors, "Validation.Error");
        return error;  // send back to client

        // todo: maybe add logging here? could help with debugging
    }

    // handles our custom business exceptions
    // these r the ones we throw ourselves
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)  // always 400 for biz errors
    public ErrorDataResult<Object> handleBusinessException(BusinessException businessException){
        // just wrap the exception in our standard format
        // use the exception class name as the error code
        ErrorDataResult<Object> error = new ErrorDataResult<>(businessException.getMessage(),
                                                            businessException.getClass().getSimpleName()+".Error");

        return error;  // send to client
    }

    // handles json parsing errors
    // happens when client sends invalid json
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)  // bad request
    public ErrorDataResult<Object> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException httpMessageNotReadableException) {
        // usually means malformed json or wrong types
        ErrorDataResult<Object> error = new ErrorDataResult<>(httpMessageNotReadableException.getMessage(),
                                                            "JsonMessageFormat.Error");  // generic error code

        return error;  // back to client
    }

    // handles illegal args
    // usually from bad method params
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400
    public ErrorDataResult<Object> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        // wrap in standard format
        ErrorDataResult<Object> error = new ErrorDataResult<>(illegalArgumentException.getMessage(),
                                                           "IllegalArgument.Error");  // generic code

        return error;  // send back
    }

    // handles db constraint violations
    // like unique key violations, foreign key issues etc
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400 again
    public ErrorDataResult<Object> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException){
        // db constraint errors - usually means duplicate key or missing foreign key
        ErrorDataResult<Object> error = new ErrorDataResult<>(dataIntegrityViolationException.getMessage(),
                                                           "DataIntegrityViolation.Error");  // generic code

        return error;  // send to client

        // these can be confusing for clients, maybe we should parse the msg
        // and make it more user-friendly? too much work for now tho
    }
/*
    // catch-all handler for any other exceptions
    // commented out cuz it's too broad and might hide real issues
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // always 400
    public ErrorDataResult<Object> handleDataIntegrityViolationException(Exception exception){
        // generic handler - not ideal but better than 500 errors
        ErrorDataResult<Object> error = new ErrorDataResult<>(exception.getMessage(),"Exception.Error");

        return error;  // send to client
    }

    // disabled for now - better to see the real errors in dev
    // maybe enable in prod with proper logging?
*/
}
