package in.lakshay.rentACarBackend.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.lakshay.rentACarBackend.entities.concretes.CarMaintenance;

import java.util.List;

// db access for car maintenance stuff
// spring data jpa is pretty sweet - no sql needed!
@Repository
public interface CarMaintenanceDao extends JpaRepository<CarMaintenance, Integer>{

    // does this record exist?
    boolean existsByMaintenanceId(int carMaintenanceId);  // for validation

    // does car have any maintenance history?
    boolean existsByCar_CarId(int carId);  // cant delete cars with maint records

    // get all records for one car
    // spring figures out the sql from method name - kinda magic
    List<CarMaintenance> findAllByCar_CarId(int carId);  // maintenance history

    // todo: maybe add date range query?
    // todo: add query for current in-shop cars?

}
