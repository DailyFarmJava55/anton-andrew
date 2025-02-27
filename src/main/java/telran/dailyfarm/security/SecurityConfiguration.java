package telran.dailyfarm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.httpBasic(Customizer.withDefaults());
    http.csrf(csrf -> csrf.disable());
    http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/auth/user/register").permitAll()
        .anyRequest().authenticated());
    return http.build();
  }
}
