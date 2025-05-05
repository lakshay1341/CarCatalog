package in.lakshay.rentACarBackend.entities.abstracts;

// base user class - all users inherit from this
// handles common stuff like auth, etc
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data  // lombok magic
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)  // inheritance mapping strat
@Table(name = "users")  // db table name
public class User {

    // primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto-increment
    @Column(name = "user_id")
    private int userId;  // pk

    // login info - email is username
    @Column(name = "email", unique = true, nullable = false)  // no dupes!
    private String email;  // also used for notifications

    // password is stored as a bcrypt hash
    // validation is done in request DTOs
    @Column(name = "password", nullable = false)
    private String password;  // bcrypt hashed password

}