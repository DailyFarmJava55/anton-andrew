package telran.dailyfarm.auth.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class UpdateUserDto {
  String phoneNumber;
  LocalDate birthDate;
  String country;
}
