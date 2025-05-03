package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for city dropdown lists
// super simple - just id and name
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityListDto {

    private int cityId;      // pk
    private String cityName;  // display name

    // todo: maybe add state/province?
    // also could add lat/long for map view

}
