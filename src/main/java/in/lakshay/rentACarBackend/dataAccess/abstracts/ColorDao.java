package in.lakshay.rentACarBackend.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.lakshay.rentACarBackend.entities.concretes.Color;

@Repository
public interface ColorDao extends JpaRepository<Color, Integer>{
	
	boolean existsByColorName(String colorName);
	boolean existsByColorId(int colorId);

}
