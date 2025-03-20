package telran.dailyfarm.user.service;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.user.dao.UserRepository;
import telran.dailyfarm.user.dto.UpdateUserDto;
import telran.dailyfarm.user.dto.UserDto;
import telran.dailyfarm.user.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  final UserRepository userRepository;
  final FarmRepository farmRepository;
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

  @Override
  public UserDto addTooFav(String farmid, Principal principal) {
    if (!farmRepository.existsById(farmid)) {
      throw new UserNotFoundException();
    }
    UserAccount user = userRepository.findById(principal.getName()).orElseThrow(UserNotFoundException::new);
    Set<String> fav = user.getFav();
    fav.add(farmid);
    userRepository.save(user);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public UserDto removeFromFav(String farmid, Principal principal) {
    if (!farmRepository.existsById(farmid)) {
      throw new UsernameNotFoundException("Farm not found");
    }
    UserAccount user = userRepository.findById(principal.getName()).orElseThrow(UserNotFoundException::new);
    Set<String> fav = user.getFav();
    fav.remove(farmid);
    userRepository.save(user);
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public List<FarmDto> getFavFarms(Principal principal) {
    Set<String> fav = userRepository.findById(principal.getName()).orElseThrow(UserNotFoundException::new).getFav();
    return farmRepository.findFarmsByIds(fav).stream().map(f -> modelMapper.map(f, FarmDto.class)).toList();
  }

}
