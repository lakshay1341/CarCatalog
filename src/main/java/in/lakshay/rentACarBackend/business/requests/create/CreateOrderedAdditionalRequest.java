package in.lakshay.rentACarBackend.business.requests.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// todo: maybe add validation for max quantity?
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalRequest {

    @NotNull
    @Min(1)  // minimum quantity is 1
    private short orderedAdditionalQuantity;

    @NotNull
    @Min(1)
    private int additionalId;  // the id of the additional service

    @JsonIgnore  // dont include this in json responses
    private int rentalCarId;

}
