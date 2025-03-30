package in.lakshay.rentACarBackend.business.requests.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@NotNull
	@Min(1)
	private double dailyPrice;
	
	@NotNull
	@Min(1900)
	private int modelYear;
	
	private String description;

	@Min(0)
	@NotNull
	private int kilometer;

	@NotNull
	@Min(1)
	private int brandId;
	
	@NotNull
	@Min(1)
	private int colorId;
	
}
