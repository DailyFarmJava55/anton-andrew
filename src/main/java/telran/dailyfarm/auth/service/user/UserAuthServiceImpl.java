package telran.dailyfarm.auth.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import telran.dailyfarm.auth.dto.LoginDto;
import telran.dailyfarm.auth.dto.LoginResponse;
import telran.dailyfarm.auth.dto.RefreshRequest;
import telran.dailyfarm.auth.dto.UserRegisterDto;
import telran.dailyfarm.auth.dto.exceptions.UserExistsExcepsion;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.security.jwt.UserJwtUtil;
import telran.dailyfarm.user.dao.UserRepository;
import telran.dailyfarm.user.dto.UserDto;
import telran.dailyfarm.user.model.UserAccount;

@Service
public class UserAuthServiceImpl implements UserAuthService {
  final UserRepository userRepository;
  final FarmRepository farmRepository;
  final ModelMapper modelMapper;
  final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManagerUser;
  final UserJwtUtil userJwtUtil;

  public UserAuthServiceImpl(UserRepository userRepository, FarmRepository farmRepository,
      ModelMapper modelMapper, PasswordEncoder passwordEncoder,
      @Qualifier("userAuthenticationManager") AuthenticationManager authenticationManagerUser,
      UserJwtUtil userJwtUtil) {
    this.userRepository = userRepository;
    this.farmRepository = farmRepository;
    this.modelMapper = modelMapper;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManagerUser = authenticationManagerUser; // Инициализация через конструктор
    this.userJwtUtil = userJwtUtil;
  }

  @Override
  public UserDto registerUser(UserRegisterDto userRegisterDto) {
    if (userRepository.existsById(userRegisterDto.getEmail())) {
      throw new UserExistsExcepsion();
    }
    if (farmRepository.existsById(userRegisterDto.getEmail())) {
      throw new RuntimeException("Farm with same email already registred");
    }
    UserAccount user = modelMapper.map(userRegisterDto, UserAccount.class);
    String pwd = passwordEncoder.encode(userRegisterDto.getPassword());
    user.setPassword(pwd);
    userRepository.save(user);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public ResponseEntity<?> loginUser(LoginDto loginDto) {
    try {
      Authentication authentication = authenticationManagerUser.authenticate(
          new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
      String token = userJwtUtil.generateToken(authentication);
      String refreshToken = userJwtUtil.generateRefreshToken(authentication);
      return ResponseEntity.ok(new LoginResponse(loginDto.getEmail(), token, refreshToken));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
  }

  @Override
  public UserDto getUser(String id) {
    UserAccount user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public ResponseEntity<?> refreshToken(RefreshRequest refreshRequest) {
    String refreshToken = refreshRequest.getRefreshToken();
    if (!userJwtUtil.validateJwtToken(refreshToken)) {
      return ResponseEntity.status(401).body("Invalid or expired refresh token");
    }
    String email = userJwtUtil.getEmailFromJwt(refreshToken);
    Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, null);
    String newToken = userJwtUtil.generateToken(authentication);
    String newRefreshToken = userJwtUtil.generateRefreshToken(authentication);
    return ResponseEntity.ok(new LoginResponse(email, newToken, newRefreshToken));
  }

}
