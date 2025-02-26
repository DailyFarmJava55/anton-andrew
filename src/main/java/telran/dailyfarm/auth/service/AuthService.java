package telran.dailyfarm.auth.service;

import java.security.Principal;

import telran.dailyfarm.auth.dto.FarmDto;
import telran.dailyfarm.auth.dto.FarmRegisterDto;
import telran.dailyfarm.auth.dto.UpdateFarmDto;
import telran.dailyfarm.auth.dto.UpdateUserDto;
import telran.dailyfarm.auth.dto.UserDto;
import telran.dailyfarm.auth.dto.UserRegisterDto;

public interface AuthService {
  UserDto registerUser(UserRegisterDto userRegisterDto);

  FarmDto registerFarm(FarmRegisterDto farmRegisterDto);

  UserDto login(Principal principal);

  UserDto getUser(String login);

  FarmDto getFarm(String id);

  UserDto updateUser(String login, UpdateUserDto updateUserDto);

  FarmDto updateFarm(String id, UpdateFarmDto updateFarmDto);

  UserDto deleteAccount(String login);

  void logout(String login);
}
