package telran.dailyfarm.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.dailyfarm.auth.dao.TokenBlacklistRepository;
import telran.dailyfarm.security.UserDetailService;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserJwtFilter extends OncePerRequestFilter {

  final UserDetailService userDetailService;
  final UserJwtUtil jwtUtil;
  final TokenBlacklistRepository tokenBlacklistRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token = null;
    String email = null;
    try {
      String headerAuth = request.getHeader("Authorization");
      if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
        token = headerAuth.substring(7);
      }
      if (tokenBlacklistRepository.existsByToken(token)) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Token invalid");
        return;
      }
      if (token != null) {
        try {
          email = jwtUtil.getEmailFromJwt(token);
        } catch (ExpiredJwtException e) {
          log.warn("JWT token has expired: {}", e.getMessage());
          response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
          return;
        }
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = userDetailService.loadUserByUsername(email);
          UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null,
              userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      }
    } catch (Exception e) {
      // TODO:
    }
    filterChain.doFilter(request, response);
  }

}
