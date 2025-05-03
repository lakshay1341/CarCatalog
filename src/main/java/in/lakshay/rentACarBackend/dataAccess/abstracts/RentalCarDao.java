package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.RentalCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// data access for rental cars
@Repository
public interface RentalCarDao extends JpaRepository<RentalCar, Integer> {
    // spring data jpa is awesome - just declare methods and it implements them!

    // existence checks
    boolean existsByRentalCarId(int rentalCarId);
    boolean existsByCar_CarId(int carId); // check if car is rented
    boolean existsByCustomer_CustomerId(int customerId); // check if customer has rentals
    boolean existsByRentalCarIdAndCar_CarId(int rentalCarId, int carId); // specific rental check

    // finders - return lists of rentals
    List<RentalCar> getAllByCar_CarId(int carId); // get rentals by car
    List<RentalCar> getAllByRentedCity_CityId(int rentedCityId); // get rentals by pickup city
    List<RentalCar> getAllByDeliveredCity_CityId(int deliveredCityId); // get rentals by dropoff city
    List<RentalCar> getAllByCustomer_CustomerId(int customerId); // get rentals by customer

    // todo: maybe add methods to find active rentals (where current date is between start and end date)
    // List<RentalCar> findByStartDateLessThanEqualAndFinishDateGreaterThanEqual(LocalDate today, LocalDate today2);
}
