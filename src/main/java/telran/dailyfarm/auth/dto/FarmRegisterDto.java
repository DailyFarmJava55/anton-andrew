package telran.dailyfarm.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.dailyfarm.farm.dto.LocationDto;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FarmRegisterDto {
  String email;
  String password;
  String farmName;
  LocationDto location;
}
