package telran.dailyfarm.surprisebag.dto;

import lombok.Getter;
import telran.dailyfarm.surprisebag.model.Status;

@Getter
public class OrderFarmDto {
  String name;
  Integer quantity;
  Double totalPrice;
  Status status;
}
