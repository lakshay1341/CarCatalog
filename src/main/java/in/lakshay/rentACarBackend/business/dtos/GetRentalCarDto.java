package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

// dto for rental car details
// used when returning a single rental's info
@Data  // lombok magic
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalCarDto {

    // basic rental info
    private int rentalCarId;  // pk
    private LocalDate startDate;    // when rental begins
    private LocalDate finishDate;   // when rental ends

    // car details
    private int carId;        // which car
    private String brandName;  // make (toyota etc)
    private String colorName;  // color (red, blue etc)

    // odometer readings
    private Integer rentedKilometer;     // starting km
    private Integer deliveredKilometer;  // ending km

    // location info
    private int rentedCityId;      // pickup location id
    private String rentedCityName;  // pickup city name
    private int deliveredCityId;    // dropoff location id
    private String deliveredCityName; // dropoff city name

    // customer contact
    private String email;  // for notifications etc

    // todo: maybe add total price? status?
}
