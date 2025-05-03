package in.lakshay.rentACarBackend.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

// handles all the model mapping stuff
// basically just a wrapper around ModelMapper with some config
@Service
public class ModelMapperManager implements ModelMapperService {

	private final ModelMapper modelMapper;  // the actual mapper we're wrapping

	public ModelMapperManager(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;  // store the injected mapper
	}

	// for mapping to DTOs - uses loose matching
	public ModelMapper forDto() {
	 // ignore ambiguity and use LOOSE matching - good for dtos where names might not match exactly
	 this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
	 return modelMapper;  // return configured mapper
	}

	// for mapping from requests - uses standard matching
	public ModelMapper forRequest() {
		 // standard matching is a bit more strict - good for requests where we want more precise mapping
		 this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		 return modelMapper;  // return the mapper
	}

	// todo: maybe add some helper methods for common mappings?
}
