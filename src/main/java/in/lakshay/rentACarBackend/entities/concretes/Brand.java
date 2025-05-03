package in.lakshay.rentACarBackend.entities.concretes;

import java.util.List;

// jpa annotations for db mapping
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// lombok makes life easier - no more boilerplate!
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // lombok generates getters, setters, equals, hashcode - so convenient!
@AllArgsConstructor // ctor with all fields
@NoArgsConstructor // default ctor
@Entity  // jpa entity marker
@Table(name = "brands")  // maps to brands table in the db
public class Brand {
	// brand entity - represents car manufacturers like Toyota, Honda, etc
	// pretty simple entity with just an id and name

	@Id  // primary key marker
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // auto increment pk
	@Column(name = "brand_id") // column name in db
	private int brandId; // pk field

	// brand name must be unique in the system
	@Column(name = "brand_name", unique = true, nullable = false)
	private String brandName; // like "Toyota", "Honda", etc

	// one brand can have many cars - 1:M relationship
	@OneToMany(mappedBy = "brand") // bidirectional relationship with Car
	private List<Car> cars;  // all cars of this brand

	// todo: maybe add country of origin?
	// todo: add logo url?
}
