package in.lakshay.rentACarBackend.business.dtos.brandDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// simple dto for brand info
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBrandDto {

	private int brandId;  // unique id
	private String brandName;   // name of the brand

}
