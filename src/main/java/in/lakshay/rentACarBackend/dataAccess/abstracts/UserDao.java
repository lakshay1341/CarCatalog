package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.abstracts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    boolean existsByUserId(int userId);
    boolean existsByEmail(String email);
    boolean existsByEmailAndUserIdIsNot(String email,int userId);



}
