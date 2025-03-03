package telran.dailyfarm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class UserSecurityConfiguration {
  private final UserDetailServiceImpl userDetailServiceImpl;
  private final FarmDetailServiceImpl farmDetailServiceImpl;

  @Bean
  SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
    WebExpressionAuthorizationManager ownerCheck = new WebExpressionAuthorizationManager(
        "authentication.name.equals(#login)");
    http.httpBasic(Customizer.withDefaults());
    http.csrf(csrf -> csrf.disable());
    http.userDetailsService(userDetailServiceImpl);
    http.authorizeHttpRequests(
        authorize -> authorize.requestMatchers("/api/auth/user/register").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/auth/user/{login}").access(ownerCheck)
            .requestMatchers(HttpMethod.DELETE, "/api/auth/user/{login}").access(ownerCheck)
            .anyRequest().authenticated());
    return http.build();
  }

  @Bean
  SecurityFilterChain farmSecurityFilterChain(HttpSecurity http) throws Exception {
    WebExpressionAuthorizationManager ownerCheck = new WebExpressionAuthorizationManager(
        "authentication.name.equals(#login)");
    http.httpBasic(Customizer.withDefaults())
        .csrf(csrf -> csrf.disable())
        .userDetailsService(farmDetailServiceImpl)
        .authorizeHttpRequests(
            authorize -> authorize.requestMatchers("/api/auth/farm/register").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/auth/farm/{login}").access(ownerCheck)
                .anyRequest().authenticated());
    return http.build();
  }
}
