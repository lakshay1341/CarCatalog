package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for getting a single additional service/item
// stuff like gps, baby seats, etc that can be added to rentals
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAdditionalDto {

    private int additionalId;  // pk

    private String additionalName;  // what is it called

    // how much it costs per day - might need to adjust for inflation lol
    private double additionalDailyPrice;

    // max number u can add to one rental
    // todo: maybe make this configurable per location?
    private short maxUnitsPerRental;
}
