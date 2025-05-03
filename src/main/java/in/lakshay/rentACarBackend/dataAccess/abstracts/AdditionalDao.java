package in.lakshay.rentACarBackend.dataAccess.abstracts;

import in.lakshay.rentACarBackend.entities.concretes.Additional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// dao for additional services (extras)
// spring data jpa makes this super easy
@Repository
public interface AdditionalDao extends JpaRepository<Additional, Integer> {

    // check if name exists - for validation
    boolean existsByAdditionalName(String additionalName);  // no dupes

    // check if id exists - for validation
    boolean existsByAdditionalId(int additionalId);  // for lookups

    // spring generates all the basic crud methods for us
    // findAll, findById, save, delete, etc

    // todo: maybe add findByNameContaining for search?

}
