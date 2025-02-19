package telran.dailyfarm.auth.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {
  @Id
  String login; // E-Mail
  String password;
  String phoneNumber;
  LocalDate birthDate;
}
