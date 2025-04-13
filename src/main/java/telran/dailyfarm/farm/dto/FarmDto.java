package telran.dailyfarm.farm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FarmDto {
  String email;
  String farmName;
  LocationDto location;
  List<String> orders;
}
