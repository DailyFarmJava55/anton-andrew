package telran.dailyfarm.user.service;

import java.security.Principal;
import java.util.List;

import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.user.dto.UpdateUserDto;
import telran.dailyfarm.user.dto.UserDto;

public interface UserService {
  UserDto updateUser(String login, UpdateUserDto updateUserDto);

  UserDto deleteAccountUser(String login);

  UserDto addTooFav(String farmid, Principal principal);

  UserDto removeFromFav(String farmid, Principal principal);

  List<FarmDto> getFavFarms(Principal principal);
}
