package telran.dailyfarm.user.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.user.dto.UpdateUserDto;
import telran.dailyfarm.user.dto.UserDto;
import telran.dailyfarm.user.service.UserService;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserControllet {

  final UserService userService;

  @PostMapping("{login}")
  public UserDto updateUser(@PathVariable String login, @RequestBody UpdateUserDto updateUserDto) {
    return userService.updateUser(login, updateUserDto);
  }

  @DeleteMapping("{login}")
  public UserDto deleteAccountUser(@PathVariable String login) {
    return userService.deleteAccountUser(login);
  }

  @GetMapping("favorite/add/{farmid}")
  public UserDto addToFav(@PathVariable String farmid, Principal principal) {
    return userService.addTooFav(farmid, principal);
  }

  @GetMapping("favorite/remove/{farmid}")
  public UserDto removeFromFav(@PathVariable String farmid, Principal principal) {
    return userService.removeFromFav(farmid, principal);
  }

  @GetMapping("favorites/get")
  public List<FarmDto> getFavFarms(Principal principal) {
    return userService.getFavFarms(principal);
  }
}
