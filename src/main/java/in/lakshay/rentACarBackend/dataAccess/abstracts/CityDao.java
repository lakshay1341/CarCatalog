package in.lakshay.rentACarBackend.dataAccess.abstracts;

// data access for cities
// spring data jpa is awesome
import in.lakshay.rentACarBackend.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // marks as spring component
public interface CityDao extends JpaRepository<City, Integer> {  // generic type is entity + id type

    // custom query methods - spring generates the sql automagically
    boolean existsByCityId(int cityId);  // check if id exists
    boolean existsByCityName(String name);  // check if name exists

    // todo: maybe add findByCityNameContaining for search?
    // or findByStateOrProvince if we add that field later

}
