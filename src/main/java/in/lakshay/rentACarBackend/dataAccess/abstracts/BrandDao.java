package in.lakshay.rentACarBackend.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.lakshay.rentACarBackend.entities.concretes.Brand;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
	
	boolean existsByBrandName(String brandName);
	boolean existsByBrandId(int brandId);

}
