package in.lakshay.rentACarBackend.api.controllers;

import in.lakshay.rentACarBackend.business.abstracts.UserService;
import in.lakshay.rentACarBackend.business.dtos.userDtos.gets.GetUserDto;
import in.lakshay.rentACarBackend.business.dtos.userDtos.lists.UserListDto;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.userExceptions.UserNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// REST controller for user operations - pretty basic stuff
// just read-only endpoints for now, no create/update/delete
@RestController
@RequestMapping("/api/users") // base endpoint
public class UsersController {

    private final UserService userService;  // service layer

    @Autowired // spring magic
    public UsersController(UserService userService) {
        this.userService = userService; // dependency injection
    }

    // might need more services later for auth etc


    // get all users - admin only
    @GetMapping("/getAll")  // GET /api/users/getAll
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResult<List<UserListDto>> getAll(){
        return this.userService.getAll(); // just pass thru to service
        // should probably add pagination at some point
    }

    // get user by id
    @GetMapping("/getById")  // GET /api/users/getById?userId=123
    public DataResult<GetUserDto> getById(@RequestParam int userId) throws UserNotFoundException {
        // todo: add better error handling here?
        return this.userService.getById(userId); // delegate to service
    }

    // TODO: add search by email endpoint?
    // TODO: add auth endpoints?

}
