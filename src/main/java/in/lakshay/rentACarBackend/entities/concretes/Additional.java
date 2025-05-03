package in.lakshay.rentACarBackend.entities.concretes;

// imports for additional entity
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;  // for one-to-many

// represents an additional service type that can be added to rentals
@Data  // lombok magic
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "additionals")  // db table name
public class Additional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto-increment
    @Column(name = "additional_id")
    private int additionalId;

    @Column(name = "additional_name",unique = true, nullable = false)  // like "GPS" or "Baby Seat"
    private String additionalName;

    @Column(name = "additional_daily_price", nullable = false)  // price per day
    private double additionalDailyPrice;

    @Column(name = "max_units_per_rental", nullable = false)  // limit per rental
    private short maxUnitsPerRental;  // e.g. max 2 baby seats per rental

    // all the ordered instances of this additional type
    @OneToMany(mappedBy = "additional")  // bidirectional relationship
    private List<OrderedAdditional> orderedAdditionals;  // all orders of this type

    // TODO: maybe add description field?
    // TODO: add active/inactive flag for seasonal items?

}
