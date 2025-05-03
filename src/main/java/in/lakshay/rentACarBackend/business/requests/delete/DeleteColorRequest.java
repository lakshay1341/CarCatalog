package in.lakshay.rentACarBackend.business.requests.delete;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// simple request for deleting colors
@Data // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class DeleteColorRequest {

	// id of color to remove
	@NotNull // cant be null obvs
	@Min(1) // needs to be valid id
	private int colorId; // primary key

	// note: should check if any cars use this color b4 deleting
}
