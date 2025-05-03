package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing additional services/items
// stuff like gps, child seats, etc that customers can add to rentals
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalListDto {

    private int additionalId;  // pk
    private String additionalName;  // what is it (gps, child seat, etc)
    private double additionalDailyPrice;  // how much per day

    // todo: maybe add availability flag?
    // would be nice to mark some items as out of stock

}
