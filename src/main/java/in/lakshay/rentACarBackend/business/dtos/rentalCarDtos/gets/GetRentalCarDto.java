package in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

// dto for getting rental car details
// used when fetching a specific rental by id
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalCarDto {

    // rental info
    private int rentalCarId;  // pk
    private LocalDate startDate;  // when rented
    private LocalDate finishDate;  // when due back

    // car details
    private int carId;  // which car
    private String brandName;  // make
    private String colorName;  // color

    // mileage tracking
    private Integer rentedKilometer;  // odo at pickup
    private Integer deliveredKilometer;  // odo at return (null if not back yet)

    // location info
    private int rentedCityId;  // pickup location
    private String rentedCityName;  // pickup city name
    private int deliveredCityId;  // return location
    private String deliveredCityName;  // return city name

    // customer contact
    private String email;  // for notifications

    // hmm should probably add payment status here too

}
