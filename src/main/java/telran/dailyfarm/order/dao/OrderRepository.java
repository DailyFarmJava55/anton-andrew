package telran.dailyfarm.order.dao;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.dailyfarm.order.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
  Stream<Order> getOrdersByCustomer(String customer);

  Stream<Order> getOrdersByCustomerAndStatus(String customer, String status);
}
