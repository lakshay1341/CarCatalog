package in.lakshay.rentACarBackend.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// basic request for removing cities from system
@Data  // lombok saves so much boilerplate!
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCityRequest {

    // id of city to delete
    @NotNull  // must provide an id
    @Min(1)   // has to be positive
    private int cityId;  // pk in db

}
