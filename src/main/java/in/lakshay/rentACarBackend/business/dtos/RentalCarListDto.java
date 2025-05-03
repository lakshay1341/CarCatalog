package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for listing rental cars
// used in the admin dashboard and reports
// this is the main listing dto for rentals
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarListDto {

    // basic rental info
    private int rentalCarId;  // pk
    private LocalDate startDate;  // when they picked it up
    private LocalDate finishDate;  // when they should return it

    // car details
    private int carId;  // which car
    private String brandName;  // make (toyota etc)
    private String colorName;  // color (red, blue etc)

    // mileage tracking
    private Integer rentedKilometer;  // odo when they got it
    private Integer deliveredKilometer;  // odo when returned (null if not back yet)

    // location info - where they got it and returned it
    private int rentedCityId;  // pickup location id
    private String rentedCityName;  // pickup city name

    private int deliveredCityId;  // dropoff location id
    private String deliveredCityName;  // dropoff city name

    // customer contact
    private String email;  // for notifications

    // todo: maybe add total price calculation?
    // would be nice to show revenue in the list
}
