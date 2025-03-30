package in.lakshay.rentACarBackend.api.controllers;

import in.lakshay.rentACarBackend.business.abstracts.UserService;
import in.lakshay.rentACarBackend.business.dtos.userDtos.gets.GetUserDto;
import in.lakshay.rentACarBackend.business.dtos.userDtos.lists.UserListDto;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getAll")
    public DataResult<List<UserListDto>> getAll(){
        return this.userService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<GetUserDto> getById(@RequestParam int userId) throws UserNotFoundException {
        return this.userService.getById(userId);
    }

}
