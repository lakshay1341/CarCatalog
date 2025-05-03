package in.lakshay.rentACarBackend.business.dtos.colorDtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// simple dto for color info
// just has id and name for now
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetColorDto {

	private int colorId;  // unique id for the color
	private String colorName;  // name like 'red', 'blue', etc

	// maybe add hex code later? idk if we need it
}
