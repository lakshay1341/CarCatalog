package in.lakshay.rentACarBackend.business.dtos.colorDtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto for listing colors in UI
// pretty basic for now
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorListDto {

    private int colorId;  // color pk
	private String colorName;  // color name like red, blue etc

	// todo: maybe add some metadata later?
}
