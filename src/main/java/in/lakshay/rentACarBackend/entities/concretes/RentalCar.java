package in.lakshay.rentACarBackend.entities.concretes;

// jackson annotation to prevent circular refs
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// lombok makes life easier!
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// jpa stuff
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data // getters, setters, equals, hashcode
@AllArgsConstructor
@NoArgsConstructor
@Entity // jpa entity
@Table(name = "rental_cars") // db table name
@JsonIgnoreProperties({"hibernateLazyInitializer","handler", "orderedAdditionals"}) // avoid circular refs
public class RentalCar {
    // represents a car rental transaction

    @Id // pk
    @GeneratedValue(strategy= GenerationType.IDENTITY) // auto-increment
    @Column(name = "rental_car_id")
    private int rentalCarId;

    // when the rental starts
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    // when the rental ends
    @Column(name = "finish_date", nullable = false)
    private LocalDate finishDate; // todo: add validation that finish > start

    // which car is being rented
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; // the actual car being rented

    // pickup city
    @ManyToOne()
    @JoinColumn(name ="rented_city", nullable = false)
    private City rentedCity;

    // dropoff city
    @ManyToOne()
    @JoinColumn(name = "delivered_city", nullable = false)
    private City deliveredCity; // might be diff from pickup city

    // odometer reading at pickup
    @Column(name = "rented_kilometer")
    private Integer rentedKilometer;

    // odometer reading at dropoff
    @Column(name = "delivered_kilometer")
    private Integer deliveredKilometer; // should be > rented_kilometer

    // extras added to rental
    @OneToMany(mappedBy = "rentalCar")
    private List<OrderedAdditional> orderedAdditionals; // like gps, child seat, etc

    // who's renting
    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // billing info
    @OneToMany(mappedBy = "rentalCar")
    private List<Invoice> invoices; // might have multiple invoices

    // payment info
    @OneToMany(mappedBy = "rentalCar")
    private List<Payment> payments; // might have multiple payments

}
