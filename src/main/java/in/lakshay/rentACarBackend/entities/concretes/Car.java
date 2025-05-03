package in.lakshay.rentACarBackend.entities.concretes;

import jakarta.persistence.*;

// lombok is awesome!
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// car entity - the main thing we rent out to customers
// this is probably the most important entity in the system
@Data  // lombok magic for getters/setters - saves so much boilerplate!
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cars")
public class Car {

	// primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	@Column(name="car_id")
	private int carId;

	// how much per day - in local currency
	@Column(name="daily_price", nullable = false)
	private double dailyPrice; // should prob use BigDecimal but whatever

	// what year is it - model year
	@Column(name="model_year", length = 4, nullable = false)
	private int modelYear; // like 2022, 2023 etc

	// whatever extra info the user wants to add
	@Column(name="description") // can be null
	private String description;

	// how many miles/km on it
	@Column(name = "kilometer", nullable = false)
	private int kilometer; // odometer reading

	// foreign keys and relationships
	@ManyToOne() // lots of cars, one brand
	@JoinColumn(name="brand_id", nullable = false)
	private Brand brand; // like Toyota, Honda etc

	@ManyToOne() // lots of cars can be same color
	@JoinColumn(name="color_id", nullable = false)
	private Color color; // red, blue, whatever


	// lists of related records - bidirectional relationships
	@OneToMany(mappedBy = "car")
	private List<CarMaintenance> carMaintenances; // when it broke

	@OneToMany(mappedBy = "car")
	private List<RentalCar> rentalCars; // who rented it

	@OneToMany(mappedBy = "car")
	private List<CarCrash> carCrashes; // oops - accidents happen

	// TODO: add image url? customers like pics
	// TODO: maybe add VIN number?
	// TODO: add plate number?
}
