package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.CarCrash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// data access for car crashes
// spring data jpa is pretty neat
@Repository
public interface CarCrashDao extends JpaRepository<CarCrash, Integer> {

    // check if crash record exists
    boolean existsByCarCrashId(int carCrashId);

    // check if car has any crashes
    boolean existsByCar_CarId(int carId);

    // get all crashes for a specific car
    // love how spring just figures this out from method name
    List<CarCrash> getAllByCar_CarId(int carId);
}
