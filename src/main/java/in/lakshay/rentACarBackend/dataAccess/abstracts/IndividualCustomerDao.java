package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// data access for individual customers (real people, not companies)
@Repository
public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer> {
    // spring data jpa is awesome - just declare methods and it implements them!

    // check if customer exists by id
    boolean existsByIndividualCustomerId(int individualCustomerId);

    // check if natl id is already used - for validation
    boolean existsByNationalIdentity(String nationalIdentity);

    // same but ignores the customer's own id - for updates
    boolean existsByNationalIdentityAndIndividualCustomerIdIsNot(String nationalIdentity, int individualCustomerId);

    // TODO: add search by name methods?
    // List<IndividualCustomer> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
