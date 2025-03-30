package in.lakshay.rentACarBackend.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	
	ModelMapper forDto();
	
	ModelMapper forRequest();

}