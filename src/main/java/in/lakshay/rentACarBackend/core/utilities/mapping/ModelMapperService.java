package in.lakshay.rentACarBackend.core.utilities.mapping;

import org.modelmapper.ModelMapper;

// interface for our mapper service
// keeps things nice and loosely coupled
public interface ModelMapperService {

	// get a mapper configured for DTOs
	// uses looser matching rules
	ModelMapper forDto();

	// get a mapper for handling requests
	// uses stricter matching rules
	ModelMapper forRequest();

	// might add more methods later if needed
}