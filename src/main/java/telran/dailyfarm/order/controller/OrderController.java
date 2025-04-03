package telran.dailyfarm.order.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.order.client.OrderClient;
import telran.dailyfarm.order.dto.OrderDto;

@RestController
@RequestMapping("/api/order/")
@RequiredArgsConstructor
public class OrderController {
  final OrderClient orderClient;

  @GetMapping("{id}/{quantity}")
  public OrderDto orderBag(@PathVariable String id, @PathVariable Integer quantity, Principal principal) {
    return orderClient.orderBag(id, quantity, principal.getName());
  }

  @GetMapping("{id}/cancel")
  public OrderDto cancelOrder(@PathVariable String id, Principal principal) {
    return orderClient.cancleOrder(id, principal.getName());
  }

  @GetMapping("get/order/{orderId}")
  public OrderDto getOrder(@PathVariable String orderId) {
    return orderClient.getOrder(orderId);
  }

  // @GetMapping("get/orders/")
  // public OrderDto[] getOrders(Principal principal) {
  // return orderService.getOrders(principal);
  // }

  // @GetMapping("get/orders/filter/status/{status}")
  // public OrderDto[] getOrdersByStatus(Principal principal, @PathVariable String
  // status) {
  // return orderService.getOrdersByStatus(principal, status);
  // }
}
