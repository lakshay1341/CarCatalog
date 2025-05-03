package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// data access for credit cards
@Repository // spring data jpa
public interface CreditCardDao extends JpaRepository<CreditCard, Integer> { // basic CRUD ops

    // helper methods for validation

    // check if card exists by id
    boolean existsByCreditCardId(int creditCardId);

    // check if customer has any cards
    boolean existsByCustomer_CustomerId(int customerId);

    // check if card number already exists in system
    // prevents duplicate cards
    boolean existsByCardNumber(String cardNumber); // should prob mask this in logs

    // get all cards for a customer
    // useful for returning customers
    List<CreditCard> getAllByCustomer_CustomerId(int customerId);

}
