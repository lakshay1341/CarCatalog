package in.lakshay.rentACarBackend.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.lakshay.rentACarBackend.entities.concretes.Car;

// data access for cars - spring data jpa magic!
// no implementation needed - spring generates it at runtime
@Repository
public interface CarDao extends JpaRepository<Car, Integer>{

	// check if exists methods - used for validation
	boolean existsByCarId(int carId);  // by primary key - basic lookup
	boolean existsByBrand_BrandId(int brandId);  // by brand - for foreign key checks
	boolean existsByColor_ColorId(int colorId);  // by color - for foreign key checks

	// finder methods - spring generates the sql for these
	List<Car> getAllByBrand_BrandId(int brandId);  // get all cars of a brand (like Toyota)
	List<Car> getAllByColor_ColorId(int colorId);  // get all cars of a color (like Red)
	List<Car> findByDailyPriceLessThanEqual(double dailyPrice);  // find cars cheaper than price - budget filter

	// TODO: add search by multiple params?
	// TODO: add full text search?
	// TODO: add custom query for advanced filtering?
}
