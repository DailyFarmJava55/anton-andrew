package telran.dailyfarm.auth.service.farm;

import org.springframework.http.ResponseEntity;

import telran.dailyfarm.auth.dto.FarmRegisterDto;
import telran.dailyfarm.auth.dto.LoginDto;
import telran.dailyfarm.farm.dto.FarmDto;

public interface FarmAuthService {
  FarmDto registerFarm(FarmRegisterDto farmRegisterDto);

  ResponseEntity<?> loginFarm(LoginDto loginDto);

  FarmDto getFarm(String id);
}
