package telran.dailyfarm.surprisebag.service.user;

import java.security.Principal;

import telran.dailyfarm.surprisebag.dto.OrderUserDto;

public interface SurprisebagServiceUser {
  OrderUserDto orderBag(String id, Integer quantity, Principal principal);
}
