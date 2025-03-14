package telran.dailyfarm.auth.controller;

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
import telran.dailyfarm.auth.dto.FarmRegisterDto;
import telran.dailyfarm.auth.dto.LoginDto;
import telran.dailyfarm.auth.model.TokenBlackList;
import telran.dailyfarm.auth.service.farm.FarmAuthService;
import telran.dailyfarm.farm.dto.FarmDto;

@RestController
@RequestMapping("/api/auth/farm/")
@RequiredArgsConstructor
public class FarmAuthController {
  final FarmAuthService farmAuthService;
  @Value("${jwt.farm.secret}")
  private String farmSecretKeyString;
  final TokenBlacklistRepository tokenBlacklistRepository;

  @PostMapping("register")
  public FarmDto registerFarm(@RequestBody FarmRegisterDto farmRegisterDto) {
    return farmAuthService.registerFarm(farmRegisterDto);
  }

  @PostMapping("login")
  public ResponseEntity<?> loginFarm(@RequestBody LoginDto loginDto) {
    return farmAuthService.loginFarm(loginDto);
  }

  @GetMapping("{id}")
  public FarmDto getFarm(@PathVariable String id) {
    return farmAuthService.getFarm(id);
  }

  @GetMapping("logout")
  public ResponseEntity<?> logoutFarm(@RequestHeader("Authorization") String authHeader) {
    SecretKey secretKey = Keys.hmacShaKeyFor(farmSecretKeyString.getBytes());
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
