package in.lakshay.rentACarBackend.business.dtos.carDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for getting car status info
// todo: maybe add more fields later?
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarStatus {

    private int carId;  // car identifier
    private int modelYear;  // year of manufacture
    private int kilometer;  // current km reading
    private String description;  // any notes abt the car

}
