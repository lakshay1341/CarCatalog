package in.lakshay.rentACarBackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// basic test class - just makes sure app loads
// todo: add actual tests at some point!
@SpringBootTest
class RentACarProjectApplicationTests {

	// this just checks if spring context loads without errors
	// not a real test but better than nothing i guess
	@Test
	void contextLoads() {
		// empty test - spring does all the work
		// should probably add assertions later
	}

	// need to add more tests:
	// - controller tests
	// - service tests
	// - integration tests
	// but who has time for that??

}
