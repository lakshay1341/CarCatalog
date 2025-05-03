package in.lakshay.rentACarBackend.dataAccess.abstracts;

// spring data jpa stuff
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// entity we're working with
import in.lakshay.rentACarBackend.entities.concretes.Color;

// data access for colors - pretty simple dao
@Repository // marks this as a spring data repository
public interface ColorDao extends JpaRepository<Color, Integer>{
	// spring data jpa magic - just declare methods and it implements them
	// no need to write any sql or implementation code!

	// check if color exists by name - used for validation
	boolean existsByColorName(String colorName); // returns true if name exists

	// check if color exists by id - used for validation
	boolean existsByColorId(int colorId); // returns true if id exists

	// might need these later - just commented out for now:
	// List<Color> findByColorNameContaining(String name); // search by partial name
	// long countByCars_CarId(int carId); // count cars with this color

	// todo: maybe add sorting methods?
}
