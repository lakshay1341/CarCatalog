package in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for listing rentals by corporate customer id
// basically shows all cars rented by a company
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarListByCorporateCustomerIdDto {

    // rental details
    private int rentalCarId;  // pk
    private LocalDate startDate;  // pickup date
    private LocalDate finishDate;  // return date

    // car info
    private int carId;  // which car
    private String brandName;  // make
    private String colorName;  // color

    // mileage
    private Integer rentedKilometer;  // odo at pickup
    private Integer deliveredKilometer;  // odo at return

    // locations
    private int rentedCityId;  // pickup city id
    private String rentedCityName;  // pickup city name
    private int deliveredCityId;  // return city id
    private String deliveredCityName;  // return city name

    // contact
    private String email;  // corp email

    // todo: add invoice ref? would be nice to link to billing
}