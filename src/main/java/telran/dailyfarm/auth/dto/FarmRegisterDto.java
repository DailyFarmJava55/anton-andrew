package telran.dailyfarm.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FarmRegisterDto {
  String login;
  String password;
  String farmName;
  LocationDto location;
}
