package telran.dailyfarm.surprisebag.service.user;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.farm.service.FarmServiceImpl;
import telran.dailyfarm.surprisebag.dao.SurprisebagRepository;
import telran.dailyfarm.order.dto.OrderDto;
import telran.dailyfarm.order.model.Order;
import telran.dailyfarm.order.model.Status;
import telran.dailyfarm.surprisebag.model.Surprisebag;
import telran.dailyfarm.user.dao.UserRepository;
import telran.dailyfarm.user.model.UserAccount;

@Service
@RequiredArgsConstructor
public class SurprisebagServiceUserImpl implements SurprisebagServiceUser {
  final SurprisebagRepository surprisebagRepository;
  final ModelMapper modelMapper;
  final UserRepository userRepository;
  final FarmServiceImpl farmServiceImpl;

  @Override
  public OrderDto orderBag(String id, Integer quantity, Principal principal) {
    Surprisebag bag = surprisebagRepository.findById(id).orElseThrow(RuntimeException::new);
    if (bag.getQuantity() < quantity) {
      throw new RuntimeException("Quantity is not enouth");
    }
    UserAccount user = userRepository.findById(principal.getName()).orElseThrow(UserNotFoundException::new);
    Order order = new Order(bag.getFarmId(), principal.getName(), quantity, Status.PENDING, bag.getName(),
        quantity * bag.getPrice());
    bag.setQuantity(bag.getQuantity() - quantity);
    user.getOrders().add(order);
    surprisebagRepository.save(bag);
    userRepository.save(user);
    farmServiceImpl.setOrders(order, bag.getFarmId());
    return modelMapper.map(order, OrderDto.class);
  }

}
