package in.lakshay.rentACarBackend.business.requests.update;

// json stuff
import com.fasterxml.jackson.annotation.JsonIgnore;

// lombok makes life easier
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// date handling
import java.time.LocalDate;

// request model for updating rental details
// used when customer wants to change dates, car, etc
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalCarRequest {

    // which rental to update
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int rentalCarId;  // pk in db

    // updated rental period
    @NotNull  // required
    private LocalDate startDate;  // when rental begins

    @NotNull  // required
    private LocalDate finishDate;  // when rental ends

    // updated car selection
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int carId;  // fk to cars table

    // updated pickup location
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int rentedCityId;  // fk to cities table

    // updated dropoff location
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int deliveredCityId;  // fk to cities table

    //todo: disabled these fields - check if correct

    // odometer readings - hidden from json
    @JsonIgnore  // dont include in json
    private Integer rentedKilometer;  // when picked up

    @JsonIgnore  // dont include in json
    private Integer deliveredKilometer;  // when returned

    // customer - shouldn't change but included
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int customerId;  // fk to customers table

    // todo: maybe add reason for change?
    // also should add audit fields

}