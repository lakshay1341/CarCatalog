package in.lakshay.rentACarBackend.business.dtos.cityDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for city listings
// pretty simple for now, just id and name
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityListDto {

    private int cityId;  // city identifier
    private String cityName;  // name of city

    // maybe add population or smth later?

}
