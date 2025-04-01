package telran.dailyfarm.auth.service.user;

import org.springframework.http.ResponseEntity;

import telran.dailyfarm.auth.dto.LoginDto;
import telran.dailyfarm.auth.dto.RefreshRequest;
import telran.dailyfarm.auth.dto.UserRegisterDto;
import telran.dailyfarm.user.dto.UserDto;

public interface UserAuthService {
  UserDto registerUser(UserRegisterDto UserRegisterDto);

  ResponseEntity<?> loginUser(LoginDto loginDto);

  UserDto getUser(String id);

  ResponseEntity<?> refreshToken(RefreshRequest refreshRequest);
}
