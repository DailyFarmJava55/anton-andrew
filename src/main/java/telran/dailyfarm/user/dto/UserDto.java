package telran.dailyfarm.user.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
  String email;
  String phoneNumber;
  LocalDate birthDate;
  String country;
  List<String> orders;
}
