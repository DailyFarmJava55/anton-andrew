package telran.dailyfarm.auth.service;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.FarmRegisterDto;
import telran.dailyfarm.auth.dto.UserRegisterDto;
import telran.dailyfarm.auth.dto.exceptions.UserExistsExcepsion;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.farm.model.FarmAccount;
import telran.dailyfarm.user.dao.UserRepository;
import telran.dailyfarm.user.dto.UserDto;
import telran.dailyfarm.user.model.UserAccount;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  final UserRepository userRepository;
  final FarmRepository farmRepository;
  final ModelMapper modelMapper;
  final PasswordEncoder passwordEncoder;

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
  public UserDto login(Principal principal) {
    // TODO: Implement login
    return null;
  }

  @Override
  public UserDto getUser(String login) {
    UserAccount user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public FarmDto getFarm(String id) {
    return modelMapper.map(farmRepository.findById(id), FarmDto.class);
  }

  @Override
  public void logout(String login) {
  }

}
