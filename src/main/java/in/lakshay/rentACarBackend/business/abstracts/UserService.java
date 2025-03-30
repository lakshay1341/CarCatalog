package in.lakshay.rentACarBackend.business.abstracts;

import in.lakshay.rentACarBackend.business.dtos.userDtos.gets.GetUserDto;
import in.lakshay.rentACarBackend.business.dtos.userDtos.lists.UserListDto;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserEmailNotValidException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;

import java.util.List;

public interface UserService {

    DataResult<List<UserListDto>> getAll();
    DataResult<GetUserDto> getById(int userId) throws UserNotFoundException;

    boolean checkIfUserIdExists(int userId) throws UserNotFoundException;
    boolean checkIfUserEmailNotExists(String email) throws UserAlreadyExistsException;
    boolean checkIfUserEmailNotExistsForUpdate(int userId, String email) throws UserEmailNotValidException;

}
