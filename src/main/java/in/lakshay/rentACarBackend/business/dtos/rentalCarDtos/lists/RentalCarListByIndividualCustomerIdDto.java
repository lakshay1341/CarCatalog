package in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for listing rentals by individual customer id
// used for personal customer history screens
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarListByIndividualCustomerIdDto {

    // rental info
    private int rentalCarId;  // pk
    private LocalDate startDate;  // when they picked up
    private LocalDate finishDate;  // when they returned/should return

    // car details
    private int carId;  // which car
    private String brandName;  // make
    private String colorName;  // color

    // mileage tracking
    private Integer rentedKilometer;  // odo at pickup
    private Integer deliveredKilometer;  // odo at return (null if not back yet)

    // location info
    private int rentedCityId;  // pickup city
    private String rentedCityName;  // pickup city name
    private int deliveredCityId;  // return city
    private String deliveredCityName;  // return city name

    // contact
    private String email;  // customer email

    // todo: add loyalty points? might be nice for the customer portal
}