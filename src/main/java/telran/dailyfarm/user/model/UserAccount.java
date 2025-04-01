package telran.dailyfarm.user.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  List<String> orders = new ArrayList<>();
  Set<String> fav = new HashSet<>();
}
