package in.lakshay.rentACarBackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// main app class - everything starts here
@SpringBootApplication
@RestControllerAdvice  // for global exception stuff
public class RentACarProjectApplication {

	// entry point - where the magic happens
	public static void main(String[] args) {
		// fire up the app!
		SpringApplication.run(RentACarProjectApplication.class, args);
	}


	// mapper for dto conversions - makes life easier
	@Bean
	public ModelMapper getModelMapper() {
		// TODO: might need to customize this later for complex mappings
		// works fine for now tho
		return new ModelMapper();
	}

	// note to self: add health check endpoint?
	// also maybe add some metrics tracking?

}
