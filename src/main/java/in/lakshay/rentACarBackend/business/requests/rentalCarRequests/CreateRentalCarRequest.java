package in.lakshay.rentACarBackend.business.requests.rentalCarRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

// request model for creating new car rentals
// used in POST /api/rentalCars/add endpoint
@Data  // lombok saves so much typing!
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalCarRequest {

    // rental period dates
    @NotNull  // required
    private LocalDate startDate;  // when rental begins

    @NotNull  // required
    private LocalDate finishDate;  // when rental ends

    // hmm, should probably validate that finish is after start
    // but that's handled in the service layer i guess

    // which car to rent
    @NotNull
    @Min(1)  // valid id only
    private int carId;  // fk to cars table

    // pickup location - typo in field name? has extra "City"
    @NotNull
    @Min(1)  // valid city id
    private int rentedCityCityId;  // where customer gets car

    // dropoff location
    @NotNull
    @Min(1)  // valid city id
    private int deliveredCityId;  // where customer returns car

    // who is renting
    @NotNull
    @Min(1)  // valid customer id
    private int customerId;  // fk to customers table

}