package in.lakshay.rentACarBackend.entities.concretes;

// lombok makes life easier!
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// jpa stuff
import jakarta.persistence.*;

@Data // getters, setters, equals, hashcode
@AllArgsConstructor
@NoArgsConstructor
@Entity // jpa entity
@Table(name = "payments") // db table name
public class Payment {
    // represents a payment for a rental

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    @Column(name = "payment_id")
    private int paymentId;

    @Column(name = "total_price", updatable = false, nullable = false) // can't change price after creation
    private double totalPrice; // total amount paid

    @ManyToOne()
    @JoinColumn(name = "rental_car_id", updatable = false, nullable = false) // fk to rental_cars
    private RentalCar rentalCar; // which rental this payment is for

    @OneToOne(mappedBy = "payment") // one-to-one with invoice
    private Invoice invoice; // the invoice for this payment

    // todo: maybe add payment method (credit card, cash, etc) and payment date?
    // private LocalDate paymentDate;
    // private String paymentMethod;
}
