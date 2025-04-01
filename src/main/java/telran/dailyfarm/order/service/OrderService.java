package telran.dailyfarm.order.service;

import java.security.Principal;

import telran.dailyfarm.order.dto.OrderDto;

public interface OrderService {
  OrderDto orderBag(String id, Integer quantity, Principal principal);

  OrderDto cancelOrder(String id, Principal principal);

  OrderDto getOrder(String orderId);

  OrderDto[] getOrders(Principal principal);

  OrderDto[] getOrdersByStatus(Principal principal, String status);
}
