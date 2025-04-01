package telran.dailyfarm.farm.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.dailyfarm.farm.dto.LocationDto;
import telran.dailyfarm.surprisebag.model.Surprisebag;

@Document(collection = "farms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FarmAccount {
  @Id
  String email;
  String password;
  String farmName;
  LocationDto location;
  Surprisebag surprisebag;
  List<String> orders = new ArrayList<>();
}
