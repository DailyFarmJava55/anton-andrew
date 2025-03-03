package telran.dailyfarm.user.service;

import telran.dailyfarm.user.dto.UpdateUserDto;
import telran.dailyfarm.user.dto.UserDto;

public interface UserService {
  UserDto updateUser(String login, UpdateUserDto updateUserDto);

  UserDto deleteAccountUser(String login);
}
