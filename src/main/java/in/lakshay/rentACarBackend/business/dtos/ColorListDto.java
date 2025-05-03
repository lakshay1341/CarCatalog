package in.lakshay.rentACarBackend.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// simple dto for color listings
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorListDto {

    private int colorId;  // primary key
	private String colorName;  // like "Red", "Blue" etc

	// maybe add hex code later for UI?
}
