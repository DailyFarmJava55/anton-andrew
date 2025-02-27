package telran.dailyfarm.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import telran.dailyfarm.auth.dto.LocationDto;

@Document(collection = "farms")
@Getter
@Setter
@AllArgsConstructor
public class Farm {
  @Id
  String email;
  String password;
  String farmName;
  LocationDto location;
}
