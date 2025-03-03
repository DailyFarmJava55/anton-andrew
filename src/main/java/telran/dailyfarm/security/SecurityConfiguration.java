package telran.dailyfarm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Autowired
  @Qualifier("userDetailsService")
  private UserDetailsService userDetailService;
  @Autowired
  @Qualifier("farmDetailsService")
  private UserDetailsService farmDetailService;

  @Bean
  SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
    http.httpBasic(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .userDetailsService(userDetailService)
        .securityMatcher("/api/auth/user/**")
        .securityMatcher("/api/user/**")
        .authorizeHttpRequests(
            authorize -> authorize.requestMatchers("/api/auth/user/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/user/{login}")
                .access(new WebExpressionAuthorizationManager("#login == authentication.name"))
                .requestMatchers(HttpMethod.DELETE, "/api/user/{login}")
                .access(new WebExpressionAuthorizationManager("#login == authentication.name"))
                .anyRequest().authenticated());
    return http.build();
  }

  @Bean
  SecurityFilterChain farmSecurityFilterChain(HttpSecurity http) throws Exception {
    http.httpBasic(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .userDetailsService(farmDetailService)
        .securityMatcher("/api/auth/farm/**")
        .securityMatcher("/api/farm/**")
        .authorizeHttpRequests(
            authorize -> authorize.requestMatchers("/api/auth/farm/register").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/farm/{login}")
                .access(new WebExpressionAuthorizationManager("#login == authentication.name")));
    return http.build();
  }
}
