package in.lakshay.rentACarBackend.business.requests.colorRequests;

// validation imports
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// lombok stuff
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request for deleting colors
// used in DELETE /api/colors/delete endpoint
@Data  // lombok magic
@AllArgsConstructor
@NoArgsConstructor
public class DeleteColorRequest {

	// which color to delete
	@NotNull  // required
	@Min(1)   // must be positive
	private int colorId;  // pk in db

	// pretty simple request - just need the id
	// validation happens in service layer to make sure
	// we don't delete colors that are in use by cars
}
