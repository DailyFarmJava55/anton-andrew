package telran.dailyfarm.order.service;

import java.security.Principal;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.order.dao.AggregationService;
import telran.dailyfarm.order.dao.OrderRepository;
import telran.dailyfarm.order.dto.OrderDto;
import telran.dailyfarm.order.dto.exceptions.OrderNotFoundException;
import telran.dailyfarm.order.model.Order;
import telran.dailyfarm.order.model.Status;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
  final ModelMapper modelMapper;
  final AggregationService aggregationService;
  final OrderRepository orderRepository;

  @Override
  public OrderDto orderBag(String id, Integer quantity, Principal principal) {
    Map<String, Object> data = aggregationService.getSurpriseBagAndFarmData(id);
    if (data == null || (int) data.get("quantity") < quantity) {
      throw new RuntimeException("Not enough quantity in the surprisebag or surprisebag not found");
    }
    Order order = orderRepository.save(Order.builder()
        .farmer(id)
        .customer(principal.getName())
        .quantity(quantity)
        .status(Status.PENDING)
        .surprisebagName((String) data.get("name"))
        .totalPrice((Double) data.get("price") * quantity)
        .build());
    aggregationService.mergeOrders(order.getId(), principal.getName(), id, quantity, false);
    return modelMapper.map(order, OrderDto.class);
  }

  @Override
  public OrderDto cancelOrder(String id, Principal principal) {
    Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    order.setStatus(Status.CANCELLED);
    orderRepository.save(order);
    aggregationService.mergeOrders(id, principal.getName(), order.getFarmer(), order.getQuantity(), true);
    return modelMapper.map(order, OrderDto.class);
  }

  @Override
  public OrderDto getOrder(String orderId) {
    return modelMapper.map(orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new), OrderDto.class);
  }

  @Override
  public OrderDto[] getOrders(Principal principal) {
    return orderRepository.getOrdersByCustomer(principal.getName()).map(o -> modelMapper.map(o, OrderDto.class))
        .toArray(OrderDto[]::new);
  }

  @Override
  public OrderDto[] getOrdersByStatus(Principal principal, String status) {
    return orderRepository.getOrdersByCustomerAndStatus(principal.getName(), status.toUpperCase())
        .map(o -> modelMapper.map(o, OrderDto.class)).toArray(OrderDto[]::new);
  }

}
