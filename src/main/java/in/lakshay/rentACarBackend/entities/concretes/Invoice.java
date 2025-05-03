package in.lakshay.rentACarBackend.entities.concretes;

// imports for invoice entity
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;  // auto timestamp

import jakarta.persistence.*;
import java.sql.Date;
import java.time.LocalDate;  // for rental dates

// represents an invoice for a car rental
@Data  // lombok ftw - no more getters/setters!
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Column(name = "invoice_id")
    private int invoiceId;

    @Column(name = "invoice_no", unique = true, length = 16, nullable = false)
    private String invoiceNo;  // unique invoice number - format YYYYMMDD + 8 random digits

    @JsonIgnore  // dont include in json responses
    @CreationTimestamp  // auto set on creation
    @Column(name = "creation_date", updatable = false) // never update this
    private Date creationDate;

    @Column(name = "start_date", nullable = false, updatable = false) // rental start
    private LocalDate startDate;

    @Column(name = "finish_date", nullable = false, updatable = false) // rental end
    private LocalDate finishDate;

    @Column(name = "total_rental_day", nullable = false, updatable = false)
    private short totalRentalDay;  // total days rented

    @Column(name = "price_of_days", nullable = false, updatable = false)
    private double priceOfDays;  // base price for days

    @Column(name = "price_of_diff_city", updatable = false) // extra fee for diff city return
    private double priceOfDiffCity;

    @Column(name = "price_of_additional", updatable = false) // extras like gps, baby seat etc
    private double priceOfAdditionals;

    @Column(name = "rental_car_total_price", nullable = false, updatable = false)
    private double rentalCarTotalPrice;  // grand total

    // relationships below

    @ManyToOne
    @JoinColumn(name = "rental_car_id", nullable = false, updatable = false)
    private RentalCar rentalCar;  // which rental this is for

    @ManyToOne
    @Cascade(CascadeType.REMOVE) // if customer deleted, remove invoices too
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
    private Customer customer;  // who rented

    @OneToOne() // 1:1 with payment
    @JoinColumn(name = "payment_id", unique = true, nullable = false, updatable = false)
    private Payment payment;  // payment details

    // TODO: maybe add invoice status enum (paid, pending, cancelled)

}
