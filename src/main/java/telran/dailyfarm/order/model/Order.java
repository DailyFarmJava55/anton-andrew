package telran.dailyfarm.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
@Builder
public class Order {
  @Id
  String id;
  String farmer;
  String customer;
  Integer quantity;
  Status status;
  String surprisebagName;
  Double totalPrice;
}
