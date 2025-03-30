package in.lakshay.rentACarBackend.business.requests.colorRequests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
	
	@NotNull
	@Min(1)
	private int colorId;
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 30)
	private String colorName;

}
