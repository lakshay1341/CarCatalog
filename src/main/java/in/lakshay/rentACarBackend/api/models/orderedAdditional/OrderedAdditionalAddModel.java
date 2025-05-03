package in.lakshay.rentACarBackend.api.models.orderedAdditional;

import in.lakshay.rentACarBackend.business.requests.creditCardRequests.CreateCreditCardRequest;
import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.CreateOrderedAdditionalRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

// model for adding extras to a rental
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalAddModel {

    // payment info
    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;  // how they'll pay

    // list of extras they want
    @NotNull
    @Valid
    List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList;  // gps, baby seats etc

    // which rental to add to
    @NotNull
    @Min(1)  // ids start at 1
    private int rentalCarId;  // must be valid rental

}
