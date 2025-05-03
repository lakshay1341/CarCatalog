package in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for listing rentals by pickup city
// helps track where cars are being rented from
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarListByRentedCityIdDto {

    // rental info
    private int rentalCarId;  // pk
    private LocalDate startDate;  // pickup date
    private LocalDate finishDate;  // return date

    // car info
    private int carId;  // which car
    private String brandName;  // make
    private String colorName;  // color

    // mileage
    private Integer rentedKilometer;  // starting odo
    private Integer deliveredKilometer;  // ending odo

    // location tracking
    private int rentedCityId;  // pickup city id
    private String rentedCityName;  // pickup city name
    private int deliveredCityId;  // return city id
    private String deliveredCityName;  // return city name

    // customer
    private String email;  // contact

    // useful for branch performance reports
}