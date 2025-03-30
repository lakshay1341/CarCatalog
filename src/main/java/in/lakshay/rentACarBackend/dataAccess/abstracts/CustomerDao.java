package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

    boolean existsByCustomerId(int customerId);
}
