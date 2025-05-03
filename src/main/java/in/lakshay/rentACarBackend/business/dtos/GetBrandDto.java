package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// simple dto for brand lookups
// just basic info for now
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBrandDto {

	private int brandId;  // pk in db

	// name like BMW, Toyota etc
	// gotta make sure this is unique in the system
	private String brandName;

	// todo: maybe add logo url?
	// also could add country of origin
}
