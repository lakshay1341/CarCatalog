package in.lakshay.rentACarBackend.entities.concretes;

import java.time.LocalDate;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// entity for tracking car maintenance records
// when cars are in the shop and cant be rented
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_maintenances")
public class CarMaintenance {

	@Id  // pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // auto-increment
	@Column(name = "maintenance_id")
	private int maintenanceId;

	@Column(name = "description", nullable = false)  // what's wrong with the car
	private String description;

	@Column(name = "return_Date")  // notice the capital D - oops
	private LocalDate returnDate;  // when car will be fixed

	// which car is being fixed
	@ManyToOne()
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;  // one car can have many maintenance records

}
