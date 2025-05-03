package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// simple dto for brand list display
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandListDto {

	private int brandId;  // pk in db
	private String brandName;   // gotta make sure this is unique

}
