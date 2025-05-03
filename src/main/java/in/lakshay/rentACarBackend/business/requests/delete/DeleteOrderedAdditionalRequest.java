package in.lakshay.rentACarBackend.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// request for removing ordered additional items
@Data // lombok magic
@AllArgsConstructor
@NoArgsConstructor
public class DeleteOrderedAdditionalRequest {

    // which ordered additional to remove
    @NotNull // cant be null
    @Min(1)  // needs valid id
    private int orderedAdditionalId;

    // might need to update invoice after this
    // but thats handled in service layer

}
