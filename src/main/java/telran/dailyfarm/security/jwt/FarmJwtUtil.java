package telran.dailyfarm.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component("farmJwtUtil")
public class FarmJwtUtil implements JwtUtil {
	@Value("${jwt.farm.secret}")
	private String farmSecretKeyString;
	@Value("${jwt.lifetime}")
	private int lifetime;

	public String generateToken(Authentication authentication) {
		SecretKey secretKey = Keys.hmacShaKeyFor(farmSecretKeyString.getBytes());
		return Jwts.builder()
				.subject(authentication.getName())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + (lifetime * 1000)))
				.signWith(secretKey)
				.compact();
	}

	public String getEmailFromJwt(String token) {
		SecretKey secretKey = Keys.hmacShaKeyFor(farmSecretKeyString.getBytes());
		Claims claims = Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return claims.getSubject();
	}
}
