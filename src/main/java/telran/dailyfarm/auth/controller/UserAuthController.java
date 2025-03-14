package telran.dailyfarm.auth.controller;

import java.security.Principal;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dao.TokenBlacklistRepository;
import telran.dailyfarm.auth.dto.LoginDto;
import telran.dailyfarm.auth.dto.UserRegisterDto;
import telran.dailyfarm.auth.model.TokenBlackList;
import telran.dailyfarm.auth.service.user.UserAuthService;
import telran.dailyfarm.user.dto.UserDto;

@RestController
@RequestMapping("/api/auth/user/")
@RequiredArgsConstructor
public class UserAuthController {
  final UserAuthService userAuthService;
  @Value("${jwt.user.secret}")
  private String userSecretKeyString;
  final TokenBlacklistRepository tokenBlacklistRepository;

  @PostMapping("register")
  public UserDto registerUser(@RequestBody UserRegisterDto userRegisterDto) {
    return userAuthService.registerUser(userRegisterDto);
  }

  @PostMapping("login")
  public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
    return userAuthService.loginUser(loginDto);
  }

  @GetMapping("{login}")
  public UserDto getUser(@PathVariable String login) {
    return userAuthService.getUser(login);
  }

  @GetMapping("logout")
  public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String authHeader) {
    SecretKey secretKey = Keys.hmacShaKeyFor(userSecretKeyString.getBytes());
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return ResponseEntity.badRequest().body("No token in header");
    }
    String token = authHeader.substring(7);
    try {
      Claims claims = Jwts.parser()
          .verifyWith(secretKey)
          .build()
          .parseSignedClaims(token)
          .getPayload();

      Date expirationDate = claims.getExpiration();
      TokenBlackList blackListedToken = new TokenBlackList(expirationDate, token);
      tokenBlacklistRepository.save(blackListedToken);
      return ResponseEntity.ok("Logout success");
    } catch (Exception e) {
      return ResponseEntity.status(401).body("Token is not valid");
    }
  }
}
