package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for city info
// pretty basic for now - just id and name
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCityDto {

    private int cityId;  // primary key

    // city name like Istanbul, Ankara etc
    // should be unique in db
    private String cityName;

    // todo: maybe add region/state?
    // also could add population or smth

}
