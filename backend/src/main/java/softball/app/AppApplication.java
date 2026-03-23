package softball.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
@EnableWebSecurity
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Primary
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("DEBUG: SecurityFilterChain BEAN WORDT NU GEMAAKT MET CORS FIX EN POST REGELS!");
		return http
				.cors(cors -> cors.configurationSource(request -> {
					var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
					corsConfiguration
							.setAllowedOrigins(java.util.List.of("https://softballu23.eu", "http://softballu23.eu",
									"http://localhost:4200"));
					corsConfiguration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
					corsConfiguration.setAllowedHeaders(java.util.List.of("*"));
					corsConfiguration.setAllowCredentials(true);
					return corsConfiguration;
				}))
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.GET, "/api/posts/**").permitAll()
						.requestMatchers(HttpMethod.POST, "/api/posts/**").hasAnyAuthority("COACH", "ROLE_COACH")
						.requestMatchers(HttpMethod.DELETE, "/api/posts/**").hasAnyAuthority("COACH", "ROLE_COACH")

						.anyRequest().permitAll())
				.build();
	}
}