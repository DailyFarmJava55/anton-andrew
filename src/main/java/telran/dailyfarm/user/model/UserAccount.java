package telran.dailyfarm.user.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.dailyfarm.surprisebag.dto.OrderUserDto;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {
  @Id
  String email;
  String password;
  String phoneNumber;
  LocalDate birthDate;
  String country;
  List<OrderUserDto> orders = new ArrayList<>();
}
