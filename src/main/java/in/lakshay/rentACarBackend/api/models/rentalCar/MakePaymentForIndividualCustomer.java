package in.lakshay.rentACarBackend.api.models.rentalCar;

import in.lakshay.rentACarBackend.business.requests.create.CreateOrderedAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.create.CreatePaymentRequest;
import in.lakshay.rentACarBackend.business.requests.create.CreateRentalCarRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePaymentForIndividualCustomer {

    @NotNull
    @Valid
    CreatePaymentRequest createPaymentRequest;

    @NotNull
    @Valid
    CreateRentalCarRequest createRentalCarRequest;

    @Valid
    List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList;

}
