package telran.dailyfarm.auth.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.FarmRegisterDto;
import telran.dailyfarm.auth.dto.UserRegisterDto;
import telran.dailyfarm.auth.service.AuthService;
import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.user.dto.UserDto;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  final AuthService authService;

  @PostMapping("/user/register")
  public UserDto registerUser(@RequestBody UserRegisterDto userRegisterDto) {
    return authService.registerUser(userRegisterDto);
  }

  @PostMapping("/farm/register")
  public FarmDto registerFarm(@RequestBody FarmRegisterDto farmRegisterDto) {
    return authService.registerFarm(farmRegisterDto);
  }

  @PostMapping("/user/login")
  public UserDto loginUser(Principal principal) {
    return authService.getUser(principal.getName());
  }

  @PostMapping("/farm/login")
  public FarmDto loginFarm(Principal principal) {
    return authService.getFarm(principal.getName());
  }

  @GetMapping("/user/{login}")
  public UserDto getUser(@PathVariable String login) {
    return authService.getUser(login);
  }

  @GetMapping("/farm/{id}")
  public FarmDto getFarm(@PathVariable String id) {
    return authService.getFarm(id);
  }

  @GetMapping("/user/logout")
  public void logoutUser(Principal principal) {
  }

  @GetMapping("/farm/logout")
  public void logoutFarm(Principal principal) {
  }
}
