package telran.dailyfarm.auth.service.farm;

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

import telran.dailyfarm.auth.dto.FarmRegisterDto;
import telran.dailyfarm.auth.dto.LoginDto;
import telran.dailyfarm.auth.dto.exceptions.UserExistsExcepsion;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.farm.model.FarmAccount;
import telran.dailyfarm.security.jwt.FarmJwtUtil;
import telran.dailyfarm.user.dao.UserRepository;

@Service
public class FarmAuthServiceImpl implements FarmAuthService {
  final FarmRepository farmRepository;
  final UserRepository userRepository;
  final PasswordEncoder passwordEncoder;
  @Qualifier("farmAuthenticationManager")
  private final AuthenticationManager authenticationManagerFarm;
  final FarmJwtUtil farmJwtUtil;
  final ModelMapper modelMapper;

  public FarmAuthServiceImpl(FarmRepository farmRepository, UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      @Qualifier("farmAuthenticationManager") AuthenticationManager authenticationManagerFarm,
      FarmJwtUtil farmJwtUtil, ModelMapper modelMapper) {
    this.farmRepository = farmRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManagerFarm = authenticationManagerFarm; // Инициализация через конструктор
    this.farmJwtUtil = farmJwtUtil;
    this.modelMapper = modelMapper;
  }

  @Override
  public FarmDto registerFarm(FarmRegisterDto farmRegisterDto) {
    if (farmRepository.existsById(farmRegisterDto.getEmail())) {
      throw new UserExistsExcepsion();
    }
    if (userRepository.existsById(farmRegisterDto.getEmail())) {
      throw new RuntimeException("User with same email already registred");
    }
    FarmAccount farm = modelMapper.map(farmRegisterDto, FarmAccount.class);
    String pwd = passwordEncoder.encode(farmRegisterDto.getPassword());
    farm.setPassword(pwd);
    farmRepository.save(farm);
    return modelMapper.map(farm, FarmDto.class);
  }

  @Override
  public ResponseEntity<?> loginFarm(LoginDto loginDto) {
    try {
      Authentication authentication = authenticationManagerFarm.authenticate(
          new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
      String token = farmJwtUtil.generateToken(authentication);
      return ResponseEntity.ok(token);
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e);
    }
  }

  @Override
  public FarmDto getFarm(String id) {
    FarmAccount farm = farmRepository.findById(id).orElseThrow(UserNotFoundException::new);
    return modelMapper.map(farm, FarmDto.class);
  }
}
