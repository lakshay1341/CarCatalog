package in.lakshay.rentACarBackend.entities.concretes;

// imports for ordered additional entity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;  // jpa stuff

// represents an ordered additional service (like GPS, baby seat, etc) for a rental
@Data  // lombok ftw - no more getters/setters!
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ordered_additionals")  // db table name
@JsonIgnoreProperties({"hibernateLazyInitializer","handler", "rentalCar"})  // avoid circular refs
public class OrderedAdditional {

    @Id  // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto-increment
    @Column(name = "ordered_additional_id")
    private int orderedAdditionalId;

    @Column(name = "ordered_additional_quantity", nullable = false)
    private short orderedAdditionalQuantity;  // how many of this item (e.g. 2 baby seats)

    @ManyToOne  // many ordered additionals can have same additional type
    @JoinColumn(name = "additional_id", nullable = false)
    private Additional additional;  // what type of additional is this

    @ManyToOne  // many ordered additionals can belong to same rental
    @JoinColumn(name = "rental_car_id", nullable = false)
    private RentalCar rentalCar;  // which rental this belongs to

    // TODO: maybe add notes field for special requests?

}
