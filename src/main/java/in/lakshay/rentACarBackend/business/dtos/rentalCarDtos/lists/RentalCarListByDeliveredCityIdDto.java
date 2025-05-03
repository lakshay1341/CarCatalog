package in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// dto for listing rentals by delivery city
// useful for tracking where cars end up
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalCarListByDeliveredCityIdDto {

    // rental info
    private int rentalCarId;  // pk
    private LocalDate startDate;  // when rented
    private LocalDate finishDate;  // when returned

    // car details
    private int carId;  // car id
    private String brandName;  // make
    private String colorName;  // color

    // mileage info
    private Integer rentedKilometer;  // starting km
    private Integer deliveredKilometer;  // ending km

    // location tracking
    private int rentedCityId;  // where rented from
    private String rentedCityName;  // pickup city name
    private int deliveredCityId;  // where returned to
    private String deliveredCityName;  // dropoff city name

    // customer
    private String email;  // contact

    // note: this is super useful for fleet rebalancing!
}