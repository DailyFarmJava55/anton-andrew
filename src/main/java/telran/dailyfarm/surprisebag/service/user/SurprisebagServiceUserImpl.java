package telran.dailyfarm.surprisebag.service.user;

import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.surprisebag.dao.SurprisebagRepository;
import telran.dailyfarm.surprisebag.dto.OrderUserDto;
import telran.dailyfarm.surprisebag.model.Status;
import telran.dailyfarm.surprisebag.model.Surprisebag;
import telran.dailyfarm.user.dao.UserRepository;
import telran.dailyfarm.user.model.UserAccount;

@Service
@RequiredArgsConstructor
public class SurprisebagServiceUserImpl implements SurprisebagServiceUser {
  final SurprisebagRepository surprisebagRepository;
  final ModelMapper modelMapper;
  final UserRepository userRepository;

  @Override
  public OrderUserDto orderBag(String id, Integer quantity, Principal principal) {
    Surprisebag bag = surprisebagRepository.findById(id).orElseThrow(RuntimeException::new);
    if (bag.getQuantity() < quantity) {
      throw new RuntimeException("Quantity is not enouth");
    }
    UserAccount user = userRepository.findById(principal.getName()).orElseThrow(UserNotFoundException::new);
    Surprisebag userBag = new Surprisebag(bag.getName(), bag.getDescription(), quantity, bag.getPrice(),
        bag.getFarmId(), Status.PENDING);
    bag.setQuantity(bag.getQuantity() - quantity);
    List<OrderUserDto> orders = user.getOrders();
    orders.add(modelMapper.map(userBag, OrderUserDto.class));
    user.setOrders(orders);
    surprisebagRepository.save(bag);
    userRepository.save(user);
    return modelMapper.map(userBag, OrderUserDto.class);
  }

}
