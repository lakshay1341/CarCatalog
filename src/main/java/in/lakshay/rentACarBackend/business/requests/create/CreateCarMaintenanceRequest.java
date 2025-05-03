package in.lakshay.rentACarBackend.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

// request for sending cars to maintenance/service
// used when cars need repairs or regular service
@Data  // lombok is awesome
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

    // what needs to be fixed/serviced
    @NotNull  // gotta tell us what's wrong
    @NotBlank // can't be empty
    @Size(min = 3, max = 300)  // reasonable length plz
    private String description;  // like "oil change" or "broken headlight"

    // when car will be back - can be null if unknown
    private LocalDate returnDate;  // optional - might not know yet

    // which car needs service
    @NotNull
    @Min(1)  // valid car id
    private int carId;  // fk to cars table

    // todo: maybe add maintenance type? regular/emergency/etc
    // also could track cost but that might be separate entity

}
