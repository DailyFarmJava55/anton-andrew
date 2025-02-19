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

@RestController
@RequestMapping("/auth")
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
  public UserDto login(Principal principal) {
    return authService.getUser(principal.getName());
  }

  @GetMapping("/user/{login}")
  public UserDto getUser(@PathVariable String login) {
    return authService.getUser(login);
  }

  @GetMapping("/farm/{id}")
  public FarmDto getFarm(@PathVariable String id) {
    return authService.getFarm(id);
  }

  @PostMapping("/user/{login}")
  public UserDto updateUser(@PathVariable String login, @RequestBody UpdateUserDto updateUserDto) {
    return authService.updateUser(login, updateUserDto);
  }

  @PostMapping("/farm/{id}")
  public FarmDto updateFarm(@PathVariable String id, @RequestBody UpdateFarmDto updateFarmDto) {
    return authService.updateFarm(id, updateFarmDto);
  }

  @DeleteMapping("/user/{login}")
  public UserDto deleteAccount(@PathVariable String login) {
    return authService.deleteAccount(login);
  }
}
