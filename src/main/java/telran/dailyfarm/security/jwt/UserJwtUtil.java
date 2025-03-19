package telran.dailyfarm.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component("userJwtUtil")
@Slf4j
public class UserJwtUtil {
	@Value("${jwt.user.secret}")
	private String userSecretKeyString;
	@Value("${jwt.lifetime}")
	private int lifetime;
	@Value("${jwt.refreshTime}")
	private int refreshTime;
	private SecretKey secretKey;

	@PostConstruct
	public void init() {
		this.secretKey = Keys.hmacShaKeyFor(userSecretKeyString.getBytes());
	}

	public String generateToken(Authentication authentication) {
		return Jwts.builder()
				.subject(authentication.getName())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + (lifetime * 1000)))
				.signWith(secretKey)
				.compact();
	}

	public String generateRefreshToken(Authentication authentication) {
		return Jwts.builder()
				.subject(authentication.getName())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + (refreshTime * 1000L)))
				.signWith(secretKey)
				.compact();
	}

	public String getEmailFromJwt(String token) {
		Claims claims = Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return claims.getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser()
					.verifyWith(secretKey)
					.build()
					.parse(authToken);
			return true;
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
}
