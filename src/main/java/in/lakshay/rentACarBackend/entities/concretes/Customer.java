package in.lakshay.rentACarBackend.entities.concretes;

// json stuff
import com.fasterxml.jackson.annotation.JsonIgnore;

// extends the User abstract class
import in.lakshay.rentACarBackend.entities.abstracts.User;

// lombok makes life easier!
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// hibernate timestamp for auto-setting registration date
import org.hibernate.annotations.CreationTimestamp;

// jpa stuff
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data // getters, setters, equals, hashcode
@AllArgsConstructor
@NoArgsConstructor
@Entity // jpa entity
@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "user_id") // joins with user table
@Inheritance(strategy = InheritanceType.JOINED) // inheritance strategy for individual/corporate customers
@Table(name = "customers") // db table name
public class Customer extends User {
    // base class for all customers (both individual and corporate)

    @Column(name = "customer_id", insertable = false, updatable = false) // same as PK
    private int customerId; // redundant but useful for queries

    @JsonIgnore // don't include in JSON responses
    @CreationTimestamp // auto-set on creation
    @Column(name = "registration_date", updatable = false) // can't change this after creation
    private LocalDate registrationDate; // when customer registered

    // customer's rental history
    @OneToMany(mappedBy = "customer") // one customer can have many rentals
    private List<RentalCar> rentedCars; // all cars rented by this customer

    // customer's billing history
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL) // cascade delete invoices when customer deleted
    private List<Invoice> invoices; // all invoices for this customer

    // customer's payment methods
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) // cascade delete cards when customer deleted
    private List<CreditCard> creditCards; // all credit cards saved by this customer

    // todo: maybe add customer status (active/inactive) field?
}
