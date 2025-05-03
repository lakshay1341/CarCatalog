package in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for listing rentals by customer id
// shows all rentals for a specific customer
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarListByCustomerIdDto {

    // rental details
    private int rentalCarId;  // pk
    private LocalDate startDate;  // when they got it
    private LocalDate finishDate;  // when they should return

    // car info
    private int carId;  // which car
    private String brandName;  // make
    private String colorName;  // color

    // mileage
    private Integer rentedKilometer;  // starting odo
    private Integer deliveredKilometer;  // ending odo

    // locations
    private int rentedCityId;  // pickup location
    private String rentedCityName;  // pickup city name
    private int deliveredCityId;  // return location
    private String deliveredCityName;  // return city name

    // contact
    private String email;  // customer email

    // hmm maybe we should add payment status here?
}
