package in.lakshay.rentACarBackend.business.dtos.additionalDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for getting a single additional service
// used when returning details about an extra
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAdditionalDto {

    // basic info
    private int additionalId;  // pk
    private String additionalName;  // what is it (gps, baby seat, etc)

    // pricing/limits
    private double additionalDailyPrice;  // cost per day
    private short maxUnitsPerRental;  // how many can be added to one rental

    // todo: maybe add description field later?
}
