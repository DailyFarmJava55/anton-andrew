package telran.dailyfarm.surprisebag.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.order.dto.OrderDto;
import telran.dailyfarm.surprisebag.service.user.SurprisebagServiceUser;

@RestController
@RequestMapping("/api/user/bag/")
@RequiredArgsConstructor
public class SurprisebagUserController {
  final SurprisebagServiceUser surprisebagServiceUser;

  @GetMapping("order/{id}/{quantity}")
  public OrderDto orderBag(@PathVariable String id, @PathVariable Integer quantity, Principal principal) {
    return surprisebagServiceUser.orderBag(id, quantity, principal);
  }
}
