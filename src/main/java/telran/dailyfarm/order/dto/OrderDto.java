package telran.dailyfarm.order.dto;

import lombok.Getter;

@Getter
public class OrderDto {
  String farmer;
  String customer;
  Integer quantity;
  Status status;
  String surprisebagName;
  Double totalPrice;
}
