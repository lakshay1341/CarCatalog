package in.lakshay.rentACarBackend.api.controllers;

import in.lakshay.rentACarBackend.dataAccess.abstracts.UserDao;
import in.lakshay.rentACarBackend.entities.abstracts.User;
import in.lakshay.rentACarBackend.security.TokenProvider;
import in.lakshay.rentACarBackend.security.UserPrincipal;
import in.lakshay.rentACarBackend.security.dto.AuthResponse;
import in.lakshay.rentACarBackend.security.dto.LoginRequest;
import in.lakshay.rentACarBackend.security.dto.SignUpRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// controller for authentication endpoints
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    // login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // authenticate with Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // set authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generate JWT token
        String token = tokenProvider.createToken(authentication);
        
        // get user details
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        // return token and user info
        return ResponseEntity.ok(new AuthResponse(
                token,
                userPrincipal.getId(),
                userPrincipal.getUsername()
        ));
    }

    // register endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        // check if email already exists
        if (userDao.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email is already taken");
        }

        // create new user
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        // save user
        User result = userDao.save(user);

        // return success response
        return ResponseEntity.ok("User registered successfully");
    }
}
