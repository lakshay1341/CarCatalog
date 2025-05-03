package in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// basic dto for listing all rentals
// used in admin dashboard and reports
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarListDto {

    // basic rental info
    private int rentalCarId;  // pk
    private LocalDate startDate;  // when rented
    private LocalDate finishDate;  // when due back

    // car details
    private int carId;  // which car
    private String brandName;  // make
    private String colorName;  // color

    // mileage info
    private Integer rentedKilometer;  // odo at pickup
    private Integer deliveredKilometer;  // odo at return

    // location info
    private int rentedCityId;  // pickup location
    private String rentedCityName;  // pickup city name
    private int deliveredCityId;  // return location
    private String deliveredCityName;  // return city name

    // customer contact
    private String email;  // for notifications

    // this is the main dto used for most rental listings
}
