package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// db stuff for payments
@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
    // spring data jpa is magic - just write method signatures and it figures it out!

    // get payments for a rental
    List<Payment> getAllByRentalCar_RentalCarId(int rentalCarId);

    // check if stuff exists
    boolean existsByPaymentId(int paymentId); // does payment exist?
    boolean existsByRentalCar_RentalCarId(int rentalCarId); // does rental have any payments?

    // todo: add these when we do reporting
    // List<Payment> findByCreatedDateBetween(LocalDate start, LocalDate end);
    // double sumTotalPriceByRentalCar_RentalCarId(int rentalCarId); // might be useful for reports

    // need to add search by customer id at some point
}
