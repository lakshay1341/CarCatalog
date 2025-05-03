package in.lakshay.rentACarBackend.business.requests.rentalCarRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// request model for updating rental car info
// used in PUT /api/rentalCars/update endpoint
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalCarRequest {

    // which rental to update
    @NotNull  // required
    @Min(1)   // valid id only
    private int rentalCarId;  // pk

    // rental period
    @NotNull  // gotta have this
    private LocalDate startDate;  // when rental begins

    @NotNull  // also required
    private LocalDate finishDate;  // when rental ends

    // which car is being rented
    @NotNull
    @Min(1)  // valid id only
    private int carId;  // fk to cars table

    // pickup location
    @NotNull
    @Min(1)  // valid city id
    private int rentedCityId;  // where customer gets car

    // dropoff location
    @NotNull
    @Min(1)  // valid city id
    private int deliveredCityId;  // where customer returns car

    // odometer readings - set by system
    @JsonIgnore  // dont include in json requests
    private Integer rentedKilometer;  // starting km

    @JsonIgnore  // dont include in json requests
    private Integer deliveredKilometer;  // ending km

    // who is renting
    @NotNull
    @Min(1)  // valid customer id
    private int customerId;  // fk to customers table

    // todo: maybe add payment status?
    // also should track if car was damaged

}