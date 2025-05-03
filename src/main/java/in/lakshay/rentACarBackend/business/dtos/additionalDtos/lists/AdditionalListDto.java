package in.lakshay.rentACarBackend.business.dtos.additionalDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing additional services
// simpler than the get dto - just basic info
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalListDto {

    private int additionalId;  // id for lookups
    private String additionalName;  // display name
    private double additionalDailyPrice;  // price per day

    // note: doesnt include max units - not needed for lists

}
