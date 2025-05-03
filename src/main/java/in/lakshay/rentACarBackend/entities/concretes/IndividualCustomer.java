package in.lakshay.rentACarBackend.entities.concretes;

// lombok annotations make life easier
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// jpa stuff
import jakarta.persistence.*;

// represents an individual person customer (vs corporate)
@Data // getters, setters, etc
@AllArgsConstructor
@NoArgsConstructor
@Entity // jpa entity
@PrimaryKeyJoinColumn(name = "individual_customer_id", referencedColumnName = "customer_id") // inheritance
@Table(name = "individua_customers") // typo in table name lol, should be individual_customers
public class IndividualCustomer extends Customer{

    // redundant id field but useful for queries
    @Column(name = "individual_customer_id", insertable = false, updatable = false)
    private int individualCustomerId;

    // person's name - required fields
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    // national ID number - must be unique
    // length=11 cuz thats standard in most countries i think?
    @Column(name = "national_identity", unique = true, length = 11, nullable = false)
    private String nationalIdentity;

    // TODO: maybe add date of birth field later

}
