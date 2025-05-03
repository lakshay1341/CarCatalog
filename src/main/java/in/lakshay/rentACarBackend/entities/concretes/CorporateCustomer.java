package in.lakshay.rentACarBackend.entities.concretes;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// jpa imports
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

// entity for corporate customers (businesses)
@Data  // lombok magic for getters/setters
@AllArgsConstructor
@NoArgsConstructor
@Entity  // jpa entity
@PrimaryKeyJoinColumn(name = "corporate_customer_id", referencedColumnName = "customer_id")  // inheritance
@Table(name = "corporate_customers")  // db table name
public class CorporateCustomer extends Customer {  // extends base customer class

    // redundant id field but useful for queries
    @Column(name = "corporate_customer_id", insertable = false, updatable = false)
    private int corporateCustomerId;

    // company info - both required
    @Column(name = "company_name", unique = true,nullable = false) // must be unique
    private String companyName;  // legal name of company

    // tax id - must be exactly 10 digits
    @Column(name = "tax_number",unique = true, length = 10, nullable = false)
    private String taxNumber;  // company tax id - unique per company
}
