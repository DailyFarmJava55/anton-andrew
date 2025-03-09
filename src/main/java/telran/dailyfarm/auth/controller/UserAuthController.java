package telran.dailyfarm.auth.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.LoginDto;
import telran.dailyfarm.auth.dto.UserRegisterDto;
import telran.dailyfarm.auth.service.user.UserAuthService;
import telran.dailyfarm.user.dto.UserDto;

@RestController
@RequestMapping("/api/auth/user/")
@RequiredArgsConstructor
public class UserAuthController {
  final UserAuthService userAuthService;

  @PostMapping("register")
  public UserDto registerUser(@RequestBody UserRegisterDto userRegisterDto) {
    return userAuthService.registerUser(userRegisterDto);
  }

  @PostMapping("login")
  public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
    return userAuthService.loginUser(loginDto);
  }

  @GetMapping("{login}")
  public UserDto getUser(@PathVariable String login) {
    return userAuthService.getUser(login);
  }

  @GetMapping("logout")
  public void logoutUser(Principal principal) {

  }
}
