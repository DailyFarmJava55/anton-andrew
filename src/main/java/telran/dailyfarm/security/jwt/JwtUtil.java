package telran.dailyfarm.security.jwt;

import org.springframework.security.core.Authentication;

public interface JwtUtil {
  String generateToken(Authentication authentication);

  String getEmailFromJwt(String token);

}
