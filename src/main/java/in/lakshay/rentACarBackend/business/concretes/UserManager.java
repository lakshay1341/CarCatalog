package in.lakshay.rentACarBackend.business.concretes;

import in.lakshay.rentACarBackend.business.abstracts.UserService;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
import in.lakshay.rentACarBackend.business.dtos.userDtos.gets.GetUserDto;
import in.lakshay.rentACarBackend.business.dtos.userDtos.lists.UserListDto;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserEmailNotValidException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.UserDao;
import in.lakshay.rentACarBackend.entities.abstracts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// basic user mgmt - nothing fancy here
@Service
public class UserManager implements UserService {

    private final UserDao userDao;
    private final ModelMapperService modelMapperService;
    private final PasswordEncoder passwordEncoder; // for hashing passwords

    @Autowired
    public UserManager(UserDao userDao, ModelMapperService modelMapperService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.modelMapperService = modelMapperService;
        this.passwordEncoder = passwordEncoder;
    }


    // get all users - pretty standard stuff
    @Override
    public DataResult<List<UserListDto>> getAll() {
        // grab everyone from db
        List<User> users = this.userDao.findAll();

        // map to DTOs - streams make this nicer but still kinda verbose
        List<UserListDto> result = users.stream().map(user -> this.modelMapperService.forDto().map(user, UserListDto.class))
                .collect(Collectors.toList());  // wish java had better collection apis tbh

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    // find user by id
    @Override
    public DataResult<GetUserDto> getById(int userId) throws UserNotFoundException {

        checkIfUserIdExists(userId);  // throws if not found

        User user = this.userDao.getById(userId); // get the user

        // convert to DTO for api response
        GetUserDto result = this.modelMapperService.forDto().map(user, GetUserDto.class);

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + userId);
    }



    // helper to check if user exists - throws if not
    @Override
    public boolean checkIfUserIdExists(int userId) throws UserNotFoundException {
        if(!this.userDao.existsByUserId(userId)){
          throw new UserNotFoundException(BusinessMessages.UserMessages.USER_ID_NOT_FOUND + userId); // bye bye
        }
        return true; // all good if we get here
    }

    // make sure email isnt already taken
    @Override
    public boolean checkIfUserEmailNotExists(String email) throws UserAlreadyExistsException {
        if(this.userDao.existsByEmail(email)){
            throw new UserAlreadyExistsException(BusinessMessages.UserMessages.USER_EMAIL_ALREAY_EXISTS + email); // typo in msg but whatever
        }
        return true; // email is available
    }

    // similar to above but for updates - need to exclude current user
    @Override
    public boolean checkIfUserEmailNotExistsForUpdate(int userId, String email) throws UserEmailNotValidException {
        if(this.userDao.existsByEmailAndUserIdIsNot(email, userId)){
            // someone else has this email already
            throw new UserEmailNotValidException(BusinessMessages.UserMessages.USER_EMAIL_ALREAY_EXISTS + email);
        }
        return true; // email is ok to use
    }

    // encode a password using bcrypt
    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    // check if a raw password matches an encoded password
    @Override
    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
