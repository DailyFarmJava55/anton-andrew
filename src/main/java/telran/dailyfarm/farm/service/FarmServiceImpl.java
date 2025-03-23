package telran.dailyfarm.farm.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.auth.dto.exceptions.UserNotFoundException;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.farm.dto.UpdateFarmDto;
import telran.dailyfarm.farm.model.FarmAccount;
import telran.dailyfarm.order.model.Order;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

  final FarmRepository farmRepository;
  final ModelMapper modelMapper;

  @Override
  public FarmDto updateFarm(String login, UpdateFarmDto updateFarmDto) {
    FarmAccount farm = farmRepository.findById(login).orElseThrow(UserNotFoundException::new);
    if (updateFarmDto.getFarmName() != null) {
      farm.setFarmName(updateFarmDto.getFarmName());
    }
    farmRepository.save(farm);
    return modelMapper.map(farm, FarmDto.class);
  }

  @Override
  public FarmDto deleteAccountFarm(String login) {
    FarmAccount farm = farmRepository.findById(login).orElseThrow(UserNotFoundException::new);
    farmRepository.deleteById(login);
    return modelMapper.map(farm, FarmDto.class);
  }

  @Override
  public List<FarmDto> findFarms() {
    return farmRepository.findAll().stream().map(f -> modelMapper.map(f, FarmDto.class)).toList();
  }

  public void setOrders(Order order, String login) {
    FarmAccount farm = farmRepository.findById(login).orElseThrow(UserNotFoundException::new);
    farm.getOrders().add(order);
    farmRepository.save(farm);
  }
}
