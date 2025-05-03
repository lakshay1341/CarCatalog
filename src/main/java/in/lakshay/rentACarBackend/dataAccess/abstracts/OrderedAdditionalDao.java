package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.OrderedAdditional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// db access for extra services/items added to rentals
@Repository
public interface OrderedAdditionalDao extends JpaRepository<OrderedAdditional, Integer> {
    // spring data jpa is pretty cool - no implementation needed

    // existence checks
    boolean existsByOrderedAdditionalId(int orderedAdditionalId); // check by id
    boolean existsByRentalCar_RentalCarId(int rentalCarId);  // check if rental has any extras
    boolean existsByAdditional_AdditionalId(int additionalId);  // check if additional is used anywhere

    // find by combo of additional type and rental
    List<OrderedAdditional> getAllByAdditional_AdditionalIdAndRentalCar_RentalCarId(int additionalId, int rentalCarId);

    // finders
    List<OrderedAdditional> getAllByRentalCar_RentalCarId(int rentalCarId);  // get all extras for a rental
    List<OrderedAdditional> getAllByAdditional_AdditionalId(int additionalId);  // get all rentals with this extra

    // todo: add method to find most popular additionals
    // long countByAdditional_AdditionalId(int additionalId);
}
