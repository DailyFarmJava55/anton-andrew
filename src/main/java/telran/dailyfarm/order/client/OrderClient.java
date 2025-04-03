package telran.dailyfarm.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import telran.dailyfarm.order.dto.OrderDto;

@FeignClient(name = "DailyFarmOrder", url = "${DailyFarmOrder.url}")
public interface OrderClient {

  @GetMapping("/api/order/{id}/{quantity}")
  OrderDto orderBag(@PathVariable String id, @PathVariable Integer quantity, @RequestHeader("X-User") String user);

  @GetMapping("/api/order/{id}/cancel")
  OrderDto cancleOrder(@PathVariable String id, String user);

  @GetMapping("/api/order/get/order/{orderId}")
  OrderDto getOrder(@PathVariable String orderId);
}
