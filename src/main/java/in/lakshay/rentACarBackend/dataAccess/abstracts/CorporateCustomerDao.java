package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// data access for corporate customers
@Repository // spring data jpa
public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer> { // basic CRUD ops

    // helper methods for validation

    // check if id exists
    boolean existsByCorporateCustomerId(int corporateCustomerId);

    // check if tax number exists - for new customers
    boolean existsByTaxNumber(String taxNumber);

    // check if tax number exists for other customers - for updates
    // makes sure we don't flag the customer's own tax number as duplicate
    boolean existsByTaxNumberAndCorporateCustomerIdIsNot(String taxNumber, int corporateCustomerId); // kinda long method name lol
}
