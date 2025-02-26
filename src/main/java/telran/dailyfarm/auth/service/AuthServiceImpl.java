package telran.dailyfarm.auth.service;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dao.AuthFarmRepository;
import telran.dailyfarm.auth.dao.AuthUserRepository;
import telran.dailyfarm.auth.dto.FarmDto;
import telran.dailyfarm.auth.dto.FarmRegisterDto;
import telran.dailyfarm.auth.dto.UpdateFarmDto;
import telran.dailyfarm.auth.dto.UpdateUserDto;
import telran.dailyfarm.auth.dto.UserDto;
import telran.dailyfarm.auth.dto.UserRegisterDto;
import telran.dailyfarm.auth.dto.exceptions.UserExistsExcepsion;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.auth.model.Farm;
import telran.dailyfarm.auth.model.User;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  final AuthUserRepository authUserRepository;
  final AuthFarmRepository authFarmRepository;
  final ModelMapper modelMapper;
  final PasswordEncoder passwordEncoder;

  @Override
  public UserDto registerUser(UserRegisterDto userRegisterDto) {
    if (authUserRepository.existsById(userRegisterDto.getLogin())) {
      throw new UserExistsExcepsion();
    }
    User user = modelMapper.map(userRegisterDto, User.class);
    String pwd = passwordEncoder.encode(userRegisterDto.getPassword());
    user.setPassword(pwd);
    authUserRepository.save(user);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public FarmDto registerFarm(FarmRegisterDto farmRegisterDto) {
    if (authFarmRepository.existsById(farmRegisterDto.getLogin())) {
      throw new UserExistsExcepsion();
    }
    Farm farm = modelMapper.map(farmRegisterDto, Farm.class);
    String pwd = passwordEncoder.encode(farmRegisterDto.getPassword());
    farm.setPassword(pwd);
    authFarmRepository.save(farm);
    return modelMapper.map(farm, FarmDto.class);
  }

  @Override
  public UserDto login(Principal principal) {
	return null;
  }

  @Override
  public UserDto getUser(String login) {
    return modelMapper.map(authUserRepository.findById(login), UserDto.class);
  }

  @Override
  public FarmDto getFarm(String id) {
    return modelMapper.map(authFarmRepository.findById(id), FarmDto.class);
  }

  @Override
  public UserDto updateUser(String login, UpdateUserDto updateUserDto) {
    User user = authUserRepository.findById(login).orElseThrow(UserNotFoundException::new);
    if (updateUserDto.getCountry() != null) {
      user.setCountry(updateUserDto.getCountry());
    }
    if (updateUserDto.getPhoneNumber() != null) {
      user.setPhoneNumber(updateUserDto.getPhoneNumber());
    }
    if (updateUserDto.getBirthDate() != null) {
      user.setBirthDate(updateUserDto.getBirthDate());
    }
    authUserRepository.save(user);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public FarmDto updateFarm(String id, UpdateFarmDto updateFarmDto) {
    Farm farm = authFarmRepository.findById(id).orElseThrow(UserNotFoundException::new);
    if (updateFarmDto.getFarmName() != null) {
      farm.setFarmName(updateFarmDto.getFarmName());
    }
    authFarmRepository.save(farm);
    return modelMapper.map(farm, FarmDto.class);
  }

  @Override
  public UserDto deleteAccount(String login) {
    User user = authUserRepository.findById(login).orElseThrow(UserNotFoundException::new);
    authUserRepository.deleteById(login);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public void logout(String login) {
  }

}
