package telran.dailyfarm.auth.dto;

import lombok.Getter;

@Getter
public class FarmDto {
  String login;
  String farmName;
  LocationDto location;
}
