package telran.dailyfarm.auth.service;

import java.security.Principal;

import telran.dailyfarm.auth.dto.FarmRegisterDto;
import telran.dailyfarm.auth.dto.UserRegisterDto;
import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.user.dto.UserDto;

public interface AuthService {
  UserDto registerUser(UserRegisterDto userRegisterDto);

  FarmDto registerFarm(FarmRegisterDto farmRegisterDto);

  UserDto login(Principal principal);

  UserDto getUser(String login);

  FarmDto getFarm(String id);

  void logout(String login);
}
