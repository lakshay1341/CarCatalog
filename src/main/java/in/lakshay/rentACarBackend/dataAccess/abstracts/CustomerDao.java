package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// db access for customer stuff
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    // spring data jpa is magic - just write method signatures and it figures it out!

    // check if customer exists
    boolean existsByCustomerId(int customerId);

    // TODO: need to add these later when we do reporting
    // List<Customer> findByRegistrationDateBefore(LocalDate date);
    // long countByRentedCarsIsNotEmpty();

    // maybe add search by email/phone?
}
