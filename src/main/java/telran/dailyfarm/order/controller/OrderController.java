package telran.dailyfarm.order.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.order.dto.OrderDto;
import telran.dailyfarm.order.service.OrderService;

@RestController
@RequestMapping("/api/order/")
@RequiredArgsConstructor
public class OrderController {
  final OrderService orderService;

  @GetMapping("{id}/{quantity}")
  public OrderDto orderBag(@PathVariable String id, @PathVariable Integer quantity, Principal principal) {
    return orderService.orderBag(id, quantity, principal);
  }

  @GetMapping("{id}/cancel")
  public OrderDto cancelOrder(@PathVariable String id, Principal principal) {
    return orderService.cancelOrder(id, principal);
  }

  @GetMapping("get/order/{orderId}")
  public OrderDto getOrder(@PathVariable String orderId) {
    return orderService.getOrder(orderId);
  }

  @GetMapping("get/orders/")
  public OrderDto[] getOrders(Principal principal) {
    return orderService.getOrders(principal);
  }

  @GetMapping("get/orders/filter/status/{status}")
  public OrderDto[] getOrdersByStatus(Principal principal, @PathVariable String status) {
    return orderService.getOrdersByStatus(principal, status);
  }
}
