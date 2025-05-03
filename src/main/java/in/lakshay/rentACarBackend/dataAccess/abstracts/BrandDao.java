package in.lakshay.rentACarBackend.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.lakshay.rentACarBackend.entities.concretes.Brand;

// data access for brands
@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
	// spring data jpa is awesome - just declare methods and it implements them!

	// check if brand exists by name
	boolean existsByBrandName(String brandName);

	// check if brand exists by id
	boolean existsByBrandId(int brandId);

	// todo: maybe add findByBrandNameContaining for search functionality?
}
