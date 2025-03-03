package telran.dailyfarm.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.user.dao.UserRepository;
import telran.dailyfarm.user.dto.UpdateUserDto;
import telran.dailyfarm.user.dto.UserDto;
import telran.dailyfarm.user.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  final UserRepository userRepository;
  final ModelMapper modelMapper;

  @Override
  public UserDto updateUser(String login, UpdateUserDto updateUserDto) {
    UserAccount user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
    if (updateUserDto.getCountry() != null) {
      user.setCountry(updateUserDto.getCountry());
    }
    if (updateUserDto.getPhoneNumber() != null) {
      user.setPhoneNumber(updateUserDto.getPhoneNumber());
    }
    if (updateUserDto.getBirthDate() != null) {
      user.setBirthDate(updateUserDto.getBirthDate());
    }
    userRepository.save(user);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public UserDto deleteAccountUser(String login) {
    UserAccount user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
    userRepository.deleteById(login);
    return modelMapper.map(user, UserDto.class);
  }

}
