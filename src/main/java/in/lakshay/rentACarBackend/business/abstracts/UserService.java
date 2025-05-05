package in.lakshay.rentACarBackend.business.abstracts;

// dtos
import in.lakshay.rentACarBackend.business.dtos.userDtos.gets.GetUserDto;
import in.lakshay.rentACarBackend.business.dtos.userDtos.lists.UserListDto;

// exceptions - so many of these!
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserEmailNotValidException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserNotFoundException;

// result wrapper
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;

import java.util.List;

// service interface for user operations
// base class for all users in the system
// implemented by UserManager
public interface UserService {

    // get all users - basic listing
    DataResult<List<UserListDto>> getAll();

    // get user by id
    DataResult<GetUserDto> getById(int userId) throws UserNotFoundException;

    // validation helpers - used by other services

    // check if user exists by id
    boolean checkIfUserIdExists(int userId) throws UserNotFoundException;

    // check if email is available (not already used)
    // returns true if email is available, false if taken
    // throws exception if email already exists
    boolean checkIfUserEmailNotExists(String email) throws UserAlreadyExistsException;

    // special version for updates - checks if email is valid for this user
    // allows keeping same email, prevents using someone else's
    boolean checkIfUserEmailNotExistsForUpdate(int userId, String email) throws UserEmailNotValidException;

    // password encoding methods

    // encode a password using bcrypt
    String encodePassword(String rawPassword);

    // check if a raw password matches an encoded password
    boolean matchesPassword(String rawPassword, String encodedPassword);

    // todo: add password reset functionality

}
