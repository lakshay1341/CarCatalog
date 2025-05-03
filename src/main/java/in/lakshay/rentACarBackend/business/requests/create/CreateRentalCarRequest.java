package in.lakshay.rentACarBackend.business.requests.create;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// date handling
import java.time.LocalDate;

// request model for creating new car rentals
// used when a customer wants to rent a car
@Data  // lombok is awesome
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalCarRequest {

    // rental period
    @NotNull  // gotta have a start date
    private LocalDate startDate;  // when rental begins

    @NotNull  // gotta have an end date
    private LocalDate finishDate;  // when rental ends

    // which car to rent
    @NotNull
    @Min(1)  // valid car id
    private int carId;  // fk to cars table

    // pickup location
    @NotNull
    @Min(1)  // valid city id
    private int rentedCityCityId;  // weird name but works

    // dropoff location
    @NotNull
    @Min(1)  // valid city id
    private int deliveredCityId;  // where car will be returned

    // who's renting
    @NotNull
    @Min(1)  // valid customer id
    private int customerId;  // fk to customers table

    // todo: maybe add special requests field?
    // also need to add payment info somewhere
}