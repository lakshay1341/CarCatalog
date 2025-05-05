package in.lakshay.rentACarBackend.dataAccess.abstracts;

// data access for users
// spring data jpa makes this super easy
import in.lakshay.rentACarBackend.entities.abstracts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  // spring component
public interface UserDao extends JpaRepository<User, Integer> {  // magic repo stuff

    // custom query methods - spring generates the sql
    boolean existsByUserId(int userId);  // check if id exists
    boolean existsByEmail(String email);  // check if email taken

    // check if email exists for any user except the one with userId
    // used for updates to prevent email conflicts
    boolean existsByEmailAndUserIdIsNot(String email, int userId);

    // find user by email - used for login/auth
    Optional<User> findByEmail(String email);  // returns empty if not found

}
