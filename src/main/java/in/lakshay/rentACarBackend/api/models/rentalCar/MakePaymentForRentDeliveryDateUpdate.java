package in.lakshay.rentACarBackend.api.models.rentalCar;

import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.business.requests.rentalCarRequests.UpdateDeliveryDateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentForRentDeliveryDateUpdate {

    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;

    @NotNull
    @Valid
    UpdateDeliveryDateRequest updateDeliveryDateRequest;
    
}
