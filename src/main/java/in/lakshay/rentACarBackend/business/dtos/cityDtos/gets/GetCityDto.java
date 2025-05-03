package in.lakshay.rentACarBackend.business.dtos.cityDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// basic city info dto
// used when retrieving city details
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCityDto {

    private int cityId;  // pk in db
    private String cityName;  // name of the city

    // might need to add state/province later?

}
