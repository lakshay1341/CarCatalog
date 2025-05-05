package in.lakshay.rentACarBackend.security;

import in.lakshay.rentACarBackend.dataAccess.abstracts.UserDao;
import in.lakshay.rentACarBackend.entities.abstracts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// loads user details from the database for Spring Security
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    // need to add findByEmail to UserDao
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find user by email (we'll need to add this method to UserDao)
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> 
                    new UsernameNotFoundException("User not found with email: " + email)
                );

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(int id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> 
                    new UsernameNotFoundException("User not found with id: " + id)
                );

        return UserPrincipal.create(user);
    }
}
