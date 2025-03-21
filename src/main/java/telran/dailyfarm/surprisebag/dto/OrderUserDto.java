package telran.dailyfarm.surprisebag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.dailyfarm.surprisebag.model.Status;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUserDto {
  String name;
  String description;
  Integer quantity;
  Double price;
  Status status;
}
