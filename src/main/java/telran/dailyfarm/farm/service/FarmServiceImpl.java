package telran.dailyfarm.farm.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.dailyfarm.farm.dao.FarmRepository;
import telran.dailyfarm.farm.dto.FarmDto;
import telran.dailyfarm.farm.dto.UpdateFarmDto;
import telran.dailyfarm.farm.dto.exceptions.FarmNotFoundException;
import telran.dailyfarm.farm.model.FarmAccount;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

  final FarmRepository farmRepository;
  final ModelMapper modelMapper;

  @Override
  public FarmDto updateFarm(String login, UpdateFarmDto updateFarmDto) {
    FarmAccount farm = farmRepository.findById(login).orElseThrow(FarmNotFoundException::new);
    if (updateFarmDto.getFarmName() != null) {
      farm.setFarmName(updateFarmDto.getFarmName());
    }
    farmRepository.save(farm);
    return modelMapper.map(farm, FarmDto.class);
  }

  @Override
  public FarmDto deleteAccountFarm(String login) {
    FarmAccount farm = farmRepository.findById(login).orElseThrow(FarmNotFoundException::new);
    farmRepository.deleteById(login);
    return modelMapper.map(farm, FarmDto.class);
  }

  @Override
  public List<FarmDto> findFarms() {
    return farmRepository.findAll().stream().map(f -> modelMapper.map(f, FarmDto.class)).toList();
  }

  public void setOrders(String orderId, String login) {
    FarmAccount farm = farmRepository.findById(login).orElseThrow(FarmNotFoundException::new);
    farm.getOrders().add(orderId);
    farmRepository.save(farm);
  }

  @Override
  public FarmAccount findFarm(String id) {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }

}
