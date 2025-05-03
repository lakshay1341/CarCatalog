package in.lakshay.rentACarBackend.business.requests.brandRequests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// request model for deleting a brand
// just need the id to delete it
@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBrandRequest {

	// validation to make sure we get a valid id
	@NotNull  // can't be null obvs
	@Min(1)   // ids start at 1 in our db
	private int brandId;  // which brand to delete

	// todo: maybe add a confirmation flag?
	// could help prevent accidental deletions

}
