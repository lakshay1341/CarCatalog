package in.lakshay.rentACarBackend.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "corporate_customer_id", referencedColumnName = "customer_id")
@Table(name = "corporate_customers")
public class CorporateCustomer extends Customer {

    @Column(name = "corporate_customer_id", insertable = false, updatable = false)
    private int corporateCustomerId;

    @Column(name = "company_name", unique = true,nullable = false)
    private String companyName;

    @Column(name = "tax_number",unique = true, length = 10, nullable = false)
    private String taxNumber;
}
