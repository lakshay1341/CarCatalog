package in.lakshay.rentACarBackend.entities.concretes;

import java.util.List;

// jpa stuff for db mapping
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// lombok - saves so much boilerplate!
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data // generates getters, setters, toString, equals, hashCode - thx lombok!
@AllArgsConstructor // constructor with all args
@NoArgsConstructor // default constructor
@Entity // jpa entity annotation
@Table(name = "colors") // maps to colors table in db
public class Color {
	// represents car colors in the system - pretty simple entity

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment id
	@Column(name = "color_id")
	private int colorId; // pk

	// color name - must be unique in the system
	@Column(name = "color_name", unique = true, nullable = false)
	private String colorName; // like "Red", "Blue", "Black" etc

	// cars with this color - bidirectional relationship with Car entity
	@OneToMany(mappedBy = "color") // one color, many cars
	private List<Car> cars; // all cars with this color

	// todo: maybe add hex code for UI display? would be nice for frontend
	// todo: add color category (primary, metallic, etc)?
}
