package telran.dailyfarm.farm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.dailyfarm.surprisebag.dto.SurprisebagDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmDto {
  String email;
  String farmName;
  LocationDto location;
  SurprisebagDto surprisebag;
}
