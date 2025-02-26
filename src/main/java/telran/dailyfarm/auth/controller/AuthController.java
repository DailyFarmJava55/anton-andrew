package telran.dailyfarm.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.FarmDto;
import telran.dailyfarm.auth.dto.FarmRegisterDto;
import telran.dailyfarm.auth.dto.UpdateFarmDto;
import telran.dailyfarm.auth.dto.UpdateUserDto;
import telran.dailyfarm.auth.dto.UserDto;
import telran.dailyfarm.auth.dto.UserRegisterDto;
import telran.dailyfarm.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  final AuthService authService;

  @PostMapping("/api/user/register")
  public UserDto registerUser(@RequestBody UserRegisterDto userRegisterDto) {
    return authService.registerUser(userRegisterDto);
  }

  @PostMapping("/api/farm/register")
  public FarmDto registerFarm(@RequestBody FarmRegisterDto farmRegisterDto) {
    return authService.registerFarm(farmRegisterDto);
  }

  @PostMapping("/api/user/login")
  public UserDto loginUser(Principal principal) {
    return authService.getUser(principal.getName());
  }

  @PostMapping("/api/farm/login")
  public FarmDto loginFarm(Principal principal) {
    return authService.getFarm(principal.getName());
  }

  @GetMapping("/api/user/{login}")
  public UserDto getUser(@PathVariable String login) {
    return authService.getUser(login);
  }

  @GetMapping("/api/farm/{id}")
  public FarmDto getFarm(@PathVariable String id) {
    return authService.getFarm(id);
  }

  @PostMapping("/api/user/{login}")
  public UserDto updateUser(@PathVariable String login, @RequestBody UpdateUserDto updateUserDto) {
    return authService.updateUser(login, updateUserDto);
  }

  @PostMapping("/api/farm/{id}")
  public FarmDto updateFarm(@PathVariable String id, @RequestBody UpdateFarmDto updateFarmDto) {
    return authService.updateFarm(id, updateFarmDto);
  }

  @DeleteMapping("/user/{login}")
  public UserDto deleteAccount(@PathVariable String login) {
    return authService.deleteAccount(login);
  }

  @GetMapping("/user/logout")
  public void logoutUser(Principal principal) {
  }

  @GetMapping("/farm/logout")
  public void logoutFarm(Principal principal) {
  }
}
