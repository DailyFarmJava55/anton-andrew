package telran.dailyfarm.surprisebag.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "surprisebag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Surprisebag {
  String name;
  String description;
  Integer quantity;
  Double price;
  @Id
  String farmId;
  // TODO: Add fields
}
