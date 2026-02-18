package be.pxl.activity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // Marks this class as a configuration class for Spring.
@EnableWebSecurity // Enables web security for the application.

public class WebSecurityConfiguration  {

    // Additional information: https://www.baeldung.com/spring-security-authentication-with-a-database
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Define a list of URLs to be permitted without authentication
        List<AntPathRequestMatcher> permittedUrls = List.of(
                new AntPathRequestMatcher("/users", "POST"),
                new AntPathRequestMatcher("/health", "GET"),
                new AntPathRequestMatcher("/swagger-ui/**", "GET"),
                new AntPathRequestMatcher("/h2-console/**")
        );

        // Configure HTTP Security

        http.csrf(AbstractHttpConfigurer::disable) // Disables CSRF protection, common in stateless REST APIs.
                .authorizeHttpRequests(authorize -> {
                    // Dynamically permit the defined URLs
                    permittedUrls.forEach(url -> authorize.requestMatchers(url).permitAll());
                    authorize.anyRequest().authenticated(); // Ensures all other requests are authenticated.
                })
                .httpBasic(withDefaults()) // Enables HTTP Basic Authentication with default settings.
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Configures session management to be stateless.


        return http.build(); // Builds and returns the SecurityFilterChain.
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
