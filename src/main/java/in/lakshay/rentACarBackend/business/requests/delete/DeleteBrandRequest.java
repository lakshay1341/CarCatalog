package in.lakshay.rentACarBackend.business.requests.delete;

// validation
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// lombok
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// simple request for deleting a car brand
// just need the id to delete
@Data  // lombok is neat
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBrandRequest {

	// which brand to delete
	@NotNull  // cant be null
	@Min(1)   // valid id plz
	private int brandId;  // pk in db

	// hmm should prob check if any cars use this brand first
	// before deleting it... but thats handled in service layer
}
