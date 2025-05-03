package in.lakshay.rentACarBackend.business.requests.delete;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// super simple request for deleting additional items
// just need the id of what to delete
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAdditionalRequest {

    // which item to delete
    @NotNull  // cant be null
    @Min(1)   // must be valid id
    private int additionalId;  // pk in db

    // todo: maybe add a reason field?
    // could be useful for auditing
}
