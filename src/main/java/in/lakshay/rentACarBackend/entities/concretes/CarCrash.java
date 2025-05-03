package in.lakshay.rentACarBackend.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

// entity for storing car crash records
// cuz accidents happen ¯\_(ツ)_/¯
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_crashes")
public class CarCrash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_crash_id")  // primary key
    private int carCrashId;

    @Column(name = "crash_date", nullable = false) // when did it happen
    private LocalDate crashDate;

    @Column(name = "crash_valuation", nullable = false) // how much damage in $$$
    private double crashValuation;  // maybe should use BigDecimal but whatevs

    // which car got smashed
    @ManyToOne()
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;  // one car can have many crashes... poor car

}
