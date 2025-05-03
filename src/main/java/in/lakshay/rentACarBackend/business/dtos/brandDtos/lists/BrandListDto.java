package in.lakshay.rentACarBackend.business.dtos.brandDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing brands in UI
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandListDto {

	private int brandId;  // primary key
	private String brandName;  // brand name like BMW, Audi etc

}
