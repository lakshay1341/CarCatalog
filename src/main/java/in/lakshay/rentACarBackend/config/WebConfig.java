package in.lakshay.rentACarBackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// cors config to fix that annoying browser security thing
// lets the frontend talk to our backend without the browser freaking out
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // grab this from props file so we can change it easily
    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;


    // setup cors stuff - this was a pain to figure out btw
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // had to google this for like an hour lol
        registry.addMapping("/**")  // everything gets cors
            .allowedOrigins(allowedOrigins)  // from props file
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // all the http verbs we need
            .allowedHeaders("*")  // just allow all headers cuz why not
            .allowCredentials(true)  // need this for cookies and auth stuff
            .maxAge(3600);  // cache for an hr to make things faster

        // TODO: maybe make this more restrictive for prod?
    }

  // might need to add more config methods here later
}
