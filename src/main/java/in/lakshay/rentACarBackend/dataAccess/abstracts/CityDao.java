package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {

    boolean existsByCityId(int cityId);
    boolean existsByCityName(String name);

}
