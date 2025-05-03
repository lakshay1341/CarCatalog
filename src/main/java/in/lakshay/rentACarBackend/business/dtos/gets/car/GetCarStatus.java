package in.lakshay.rentACarBackend.business.dtos.gets.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// just a simple dto for car status
// nothing fancy here
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarStatus {

    private int carId;  // unique id
    private int modelYear;   // when it was made

    private int kilometer; // how far it's gone
    private String description;  // any notes

    //todo:gotta add damage field here later
    // maybe add maintenance history too? idk

}
