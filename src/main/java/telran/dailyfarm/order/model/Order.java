package telran.dailyfarm.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  String farmer;
  String customer;
  Integer quantity;
  Status status;
  String surprisebagName;
  Double totalPrice;
}
