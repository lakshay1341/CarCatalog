package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// simple dto for getting color info
// just basic stuff for now
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetColorDto {

	private int colorId;  // pk in db
	private String colorName;  // like red, blue etc

	// todo: maybe add hex code later? not sure if needed
	// could be useful for UI stuff tho
}
