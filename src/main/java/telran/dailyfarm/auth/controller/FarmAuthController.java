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
import telran.dailyfarm.auth.dto.FarmRegisterDto;
import telran.dailyfarm.auth.dto.LoginDto;
import telran.dailyfarm.auth.service.farm.FarmAuthService;
import telran.dailyfarm.farm.dto.FarmDto;

@RestController
@RequestMapping("/api/auth/farm/")
@RequiredArgsConstructor
public class FarmAuthController {
  final FarmAuthService farmAuthService;

  @PostMapping("register")
  public FarmDto registerFarm(@RequestBody FarmRegisterDto farmRegisterDto) {
    return farmAuthService.registerFarm(farmRegisterDto);
  }

  @PostMapping("login")
  public ResponseEntity<?> loginFarm(@RequestBody LoginDto loginDto) {
    return farmAuthService.loginFarm(loginDto);
  }

  @GetMapping("{id}")
  public FarmDto getFarm(@PathVariable String id) {
    return farmAuthService.getFarm(id);
  }

  @GetMapping("/farm/logout")
  public void logoutFarm(Principal principal) {
  }

}
