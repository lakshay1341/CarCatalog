package in.lakshay.rentACarBackend.entities.concretes;

// entity for cities where cars can be rented/returned
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data // lombok saves so much time
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cities") // maps to cities table in db
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Column(name = "city_id")
    private int cityId;  // pk

    @Column(name = "city_name", unique = true, nullable = false) // city names must be unique!
    private String cityName;  // like "Istanbul", "Ankara" etc

    // bidirectional relationships - might cause json issues?
    @OneToMany(mappedBy = "rentedCity") // cars rented from this city
    private List<RentalCar> rentedCarsFromCity;

    @OneToMany(mappedBy = "deliveredCity") // cars returned to this city
    private List<RentalCar> deliveredCarsToCity;

    // todo: maybe add region/state later?
}
