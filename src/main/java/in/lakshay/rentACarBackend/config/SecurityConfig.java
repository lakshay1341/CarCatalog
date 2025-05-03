package in.lakshay.rentACarBackend.config;

// spring security stuff
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// basic security config
// super simple for now - just disables csrf and allows api access
// todo: add proper auth later with JWT
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // configure security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // disable csrf for api endpoints
        // not great for prod but fine for dev
        http
            .csrf(csrf -> csrf.disable())  // disable csrf - not ideal but ok for now
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()  // allow all api endpoints
                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/webjars/**").permitAll()  // swagger stuff
                .anyRequest().authenticated()  // everything else needs auth
            );

        // build and return the config
        return http.build();  // this is temporary, need real auth later!
    }

    // note: we should add password encoder and auth provider beans
    // but that's for later when we implement real auth
}