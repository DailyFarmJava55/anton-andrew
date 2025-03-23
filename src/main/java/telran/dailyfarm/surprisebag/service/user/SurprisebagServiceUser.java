package telran.dailyfarm.surprisebag.service.user;

import java.security.Principal;

import telran.dailyfarm.order.dto.OrderDto;

public interface SurprisebagServiceUser {
  OrderDto orderBag(String id, Integer quantity, Principal principal);
}
